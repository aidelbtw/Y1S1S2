import java.util.*;

// ================================================================
//  GoodTech Smart Food Delivery & Order Management System
//
//  Data Structures used:
//    1. Linked List     -> Customer & Restaurant management
//    2. Stack + Queue   -> Order building (undo) + processing (FIFO)
//    3. Priority Queue  -> Rider assignment (Min Heap by distance)
//    4. Graph + Dijkstra-> Shortest delivery route (XY coordinate map)
//    5. BST             -> Food menu (alphabetical search & display)
//    6. HashMap         -> Fast O(1) lookup for customers & orders
// ================================================================

public class SmartFoodDelivery {

    // ============================================================
    //  SECTION 1 — CITY MAP (Graph with XY Coordinates)
    //
    //  Every location has a real (x, y) position on a grid.
    //  Roads connect some locations — NOT every location is
    //  directly connected to every other (like a real city).
    //
    //  Road distance = Euclidean distance calculated from coords:
    //    dist = sqrt( (x2-x1)^2 + (y2-y1)^2 )
    //
    //  Stored as an adjacency matrix (2D array):
    //    roads[i][j] = distance between location i and j
    //    roads[i][j] = -1 means no direct road exists
    // ============================================================

    static class Location {
        String name;
        int x, y;
        Location(String name, int x, int y) {
            this.name = name;
            this.x    = x;
            this.y    = y;
        }
    }

    // City locations — index = ID used everywhere in the program
    static Location[] locs = {
        new Location("Restaurant District",  2, 1),  // [0]
        new Location("East Junction",        5, 2),  // [1]
        new Location("West Market",          0, 3),  // [2]
        new Location("City Center",          4, 4),  // [3]
        new Location("North Junction",       2, 5),  // [4]
        new Location("Eastview Residences",  7, 4),  // [5]
        new Location("Northpark Residences", 1, 7),  // [6]
    };

    static int N = locs.length; // number of locations
    static double[][] roads = new double[N][N];

    // Set up the road network
    static void initCity() {
        for (double[] row : roads) Arrays.fill(row, -1); // -1 = no road
        for (int i = 0; i < N; i++) roads[i][i] = 0;    //  0 = same location

        // Connect roads (each call creates a two-way road)
        connectRoad(0, 1); // Restaurant District  <-> East Junction
        connectRoad(0, 2); // Restaurant District  <-> West Market
        connectRoad(1, 3); // East Junction        <-> City Center
        connectRoad(1, 5); // East Junction        <-> Eastview Residences
        connectRoad(2, 3); // West Market          <-> City Center
        connectRoad(3, 4); // City Center          <-> North Junction
        connectRoad(1, 4); // East Junction        <-> North Junction
        connectRoad(4, 6); // North Junction       <-> Northpark Residences
    }

    // Calculate straight-line distance between two locations using coordinates
    static double calcDist(int a, int b) {
        double dx = locs[a].x - locs[b].x;
        double dy = locs[a].y - locs[b].y;
        return Math.round(Math.sqrt(dx * dx + dy * dy) * 10.0) / 10.0;
    }

    static void connectRoad(int a, int b) {
        double d = calcDist(a, b);
        roads[a][b] = d;
        roads[b][a] = d;
    }

    // Print the city as a simple ASCII coordinate grid
    static void printMap() {
        System.out.println("\n  City Grid Map:");
        System.out.println("  Y");
        for (int y = 7; y >= 0; y--) {
            System.out.printf("  %d |", y);
            for (int x = 0; x <= 8; x++) {
                String cell = " . ";
                for (int i = 0; i < N; i++) {
                    if (locs[i].x == x && locs[i].y == y) {
                        cell = "[" + i + "]";
                        break;
                    }
                }
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println("    +---------------------------> X\n");
        for (int i = 0; i < N; i++) {
            System.out.printf("  [%d] %-28s (%d, %d)%n",
                i, locs[i].name, locs[i].x, locs[i].y);
        }
    }

    // ============================================================
    //  DIJKSTRA'S ALGORITHM
    //
    //  Finds the shortest path from 'start' to 'end'.
    //
    //  How it works:
    //  1. Set distance to start = 0, all others = infinity
    //  2. Pick the unvisited location with the smallest distance
    //  3. For each of its connected neighbours, check:
    //       "Is going through here shorter than the current path?"
    //     If yes -> update that neighbour's distance
    //  4. Mark location as visited, repeat until 'end' is reached
    //  5. Backtrack using prev[] to reconstruct the full path
    //
    //  Time complexity: O(V^2) with adjacency matrix
    // ============================================================

    static void dijkstra(int start, int end) {
        double[]  dist    = new double[N];
        int[]     prev    = new int[N];   // tracks which node we came from
        boolean[] visited = new boolean[N];

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        for (int step = 0; step < N; step++) {
            // Find the unvisited location with the smallest known distance
            int u = -1;
            for (int v = 0; v < N; v++) {
                if (!visited[v] && (u == -1 || dist[v] < dist[u])) u = v;
            }
            if (u == -1 || dist[u] == Double.MAX_VALUE) break;
            visited[u] = true;

            // Relax all roads from u
            for (int v = 0; v < N; v++) {
                if (!visited[v] && roads[u][v] > 0) { // roads[u][v] > 0 means a real road exists
                    double newDist = dist[u] + roads[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u; // record that we arrived at v through u
                    }
                }
            }
        }

        if (dist[end] == Double.MAX_VALUE) {
            System.out.println("  No route found between these locations.");
            return;
        }

        // Reconstruct the path by following prev[] backwards from end
        List<String> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(locs[at].name);
        }
        Collections.reverse(path);

        System.out.println("  Route    : " + String.join(" → ", path));
        System.out.printf ("  Distance : %.1f km%n", dist[end]);
    }

    // ============================================================
    //  SECTION 2 — FOOD ITEM & BST (Binary Search Tree)
    //
    //  Each restaurant has its OWN BST for its menu.
    //  Items are inserted alphabetically:
    //    - Name comes before root  → go LEFT
    //    - Name comes after root   → go RIGHT
    //
    //  Search: O(log n) — at each node we eliminate half the tree
    //  Display in A-Z order: inorder traversal (left → root → right)
    // ============================================================

    static class FoodItem {
        String name;
        double price;
        FoodItem(String name, double price) {
            this.name  = name;
            this.price = price;
        }
    }

    static class BSTNode {
        FoodItem item;
        BSTNode left, right;
        BSTNode(FoodItem item) { this.item = item; }
    }

    // Insert into BST, maintaining alphabetical order
    static BSTNode bstInsert(BSTNode root, FoodItem item) {
        if (root == null) return new BSTNode(item);
        int cmp = item.name.compareToIgnoreCase(root.item.name);
        if      (cmp < 0) root.left  = bstInsert(root.left,  item);
        else if (cmp > 0) root.right = bstInsert(root.right, item);
        else System.out.println("  '" + item.name + "' already exists in menu.");
        return root;
    }

    // Search BST by name — returns FoodItem or null
    static FoodItem bstSearch(BSTNode root, String name) {
        if (root == null) return null;
        int cmp = name.compareToIgnoreCase(root.item.name);
        if      (cmp == 0) return root.item;
        else if (cmp <  0) return bstSearch(root.left,  name);
        else               return bstSearch(root.right, name);
    }

    // Inorder traversal: prints menu in A-Z order
    static void bstDisplay(BSTNode root, int[] count) {
        if (root == null) return;
        bstDisplay(root.left, count);
        count[0]++;
        System.out.printf("    %2d. %-24s RM%.2f%n",
            count[0], root.item.name, root.item.price);
        bstDisplay(root.right, count);
    }

    // ============================================================
    //  SECTION 3 — CUSTOMER (Linked List)
    //
    //  Each Customer has:
    //    - ID, name, phone
    //    - locIdx: which city location they live at (index into locs[])
    //
    //  Linked List operations:
    //    add()    -> walk to last node, attach new node   O(n)
    //    remove() -> find node, skip over it              O(n)
    //    display()-> walk from head to tail               O(n)
    //
    //  Why Linked List over Array?
    //    No shifting needed when adding/removing in the middle.
    // ============================================================

    static class Customer {
        String id, name, phone;
        int locIdx;
        Customer(String id, String name, String phone, int locIdx) {
            this.id     = id;
            this.name   = name;
            this.phone  = phone;
            this.locIdx = locIdx;
        }
        String locName() { return locs[locIdx].name; }
    }

    // A single node in the Customer linked list
    static class CNode {
        Customer data;
        CNode next;
        CNode(Customer data) { this.data = data; }
    }

    static class CustomerList {
        CNode head;
        int size = 0;

        void add(Customer c) {
            CNode newNode = new CNode(c);
            if (head == null) {
                head = newNode;
            } else {
                CNode cur = head;
                while (cur.next != null) cur = cur.next;
                cur.next = newNode;
            }
            size++;
        }

        boolean remove(String id) {
            if (head == null) return false;
            if (head.data.id.equals(id)) { head = head.next; size--; return true; }
            CNode cur = head;
            while (cur.next != null) {
                if (cur.next.data.id.equals(id)) {
                    cur.next = cur.next.next;
                    size--;
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        void display() {
            if (head == null) { System.out.println("  No customers on record."); return; }
            System.out.printf("\n  %-6s %-16s %-14s %-28s %s%n",
                "ID", "Name", "Phone", "Location", "Coords");
            CNode cur = head;
            while (cur != null) {
                Customer c = cur.data;
                System.out.printf("  %-6s %-16s %-14s %-28s (%d, %d)%n",
                    c.id, c.name, c.phone, c.locName(),
                    locs[c.locIdx].x, locs[c.locIdx].y);
                cur = cur.next;
            }
            System.out.println("  Total: " + size + " customer(s)");
        }
    }

    // ============================================================
    //  SECTION 4 — RESTAURANT (Linked List, each with BST menu)
    // ============================================================

    static class Restaurant {
        String  id, name;
        int     locIdx;
        BSTNode menuRoot = null; // this restaurant's BST food menu

        Restaurant(String id, String name, int locIdx) {
            this.id     = id;
            this.name   = name;
            this.locIdx = locIdx;
        }

        void addToMenu(String foodName, double price) {
            menuRoot = bstInsert(menuRoot, new FoodItem(foodName, price));
        }

        String locName() { return locs[locIdx].name; }
    }

    static class RNode {
        Restaurant data;
        RNode next;
        RNode(Restaurant data) { this.data = data; }
    }

    static class RestaurantList {
        RNode head;
        int size = 0;

        void add(Restaurant r) {
            RNode newNode = new RNode(r);
            if (head == null) {
                head = newNode;
            } else {
                RNode cur = head;
                while (cur.next != null) cur = cur.next;
                cur.next = newNode;
            }
            size++;
        }

        boolean remove(String id) {
            if (head == null) return false;
            if (head.data.id.equals(id)) { head = head.next; size--; return true; }
            RNode cur = head;
            while (cur.next != null) {
                if (cur.next.data.id.equals(id)) {
                    cur.next = cur.next.next;
                    size--;
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        Restaurant findById(String id) {
            RNode cur = head;
            while (cur != null) {
                if (cur.data.id.equals(id)) return cur.data;
                cur = cur.next;
            }
            return null;
        }

        void display() {
            if (head == null) { System.out.println("  No restaurants on record."); return; }
            System.out.printf("\n  %-6s %-20s %-28s %s%n",
                "ID", "Name", "Location", "Coords");
            RNode cur = head;
            while (cur != null) {
                Restaurant r = cur.data;
                System.out.printf("  %-6s %-20s %-28s (%d, %d)%n",
                    r.id, r.name, r.locName(),
                    locs[r.locIdx].x, locs[r.locIdx].y);
                cur = cur.next;
            }
            System.out.println("  Total: " + size + " restaurant(s)");
        }
    }

    // ============================================================
    //  SECTION 5 — ORDER ITEM & ORDER
    //
    //  OrderItem: one food item + quantity (goes into Stack/Queue)
    //  Order:     full confirmed order with customer, restaurant,
    //             list of items, total price, and status tracking
    //
    //  Status flow: QUEUED → PROCESSING → RIDER ASSIGNED
    // ============================================================

    static class OrderItem {
        String foodName;
        double price;
        int    qty;

        OrderItem(String foodName, double price, int qty) {
            this.foodName = foodName;
            this.price    = price;
            this.qty      = qty;
        }

        @Override
        public String toString() {
            return String.format("%-24s x%-3d  RM%.2f", foodName, qty, price * qty);
        }
    }

    static class Order {
        String         orderId;
        Customer       customer;
        Restaurant     restaurant;
        List<OrderItem> items;
        double         total;
        String         status;
        String         assignedRider = null;

        Order(String id, Customer c, Restaurant r, List<OrderItem> items) {
            this.orderId    = id;
            this.customer   = c;
            this.restaurant = r;
            this.items      = new ArrayList<>(items);
            this.status     = "QUEUED";
            this.total      = 0;
            for (OrderItem oi : items) total += oi.price * oi.qty;
        }

        void display() {
            System.out.printf ("    Order ID   : %-30s %n", orderId);
            System.out.printf ("    Customer   : %-30s %n", customer.name + " (" + customer.locName() + ")");
            System.out.printf ("    Restaurant : %-30s %n", restaurant.name + " (" + restaurant.locName() + ")");
            System.out.printf ("    Status     : %-30s %n", status);
            if (assignedRider != null)
                System.out.printf("    Rider      : %-30s %n", assignedRider);
            System.out.println("    Items:                                      ");
            for (OrderItem oi : items)
                System.out.printf("      - %-40s %n", oi.toString());
            System.out.printf ("    Total      : RM%-29.2f %n", total);
        }
    }

    // ============================================================
    //  SECTION 6 — RIDER + PRIORITY QUEUE (Min Heap)
    //
    //  When an order needs a rider, we build a min-heap of all
    //  AVAILABLE riders sorted by their distance to the restaurant.
    //
    //  The heap always puts the NEAREST rider at the top, so
    //  we get the best rider in O(log n) without looping through
    //  every rider manually (which would be O(n) linear search).
    // ============================================================

    static class Rider {
        String  id, name, phone;
        int     locIdx;
        boolean available = true;

        Rider(String id, String name, String phone, int locIdx) {
            this.id     = id;
            this.name   = name;
            this.phone  = phone;
            this.locIdx = locIdx;
        }
    }

    // Finds the nearest available rider to a restaurant using PriorityQueue
    static Rider assignNearestRider(int restaurantLoc) {
        // Build a min-heap: riders sorted by distance to the restaurant
        // Comparator: a comes before b if a's distance < b's distance
        PriorityQueue<Rider> pq = new PriorityQueue<>(
            Comparator.comparingDouble(r -> calcDist(r.locIdx, restaurantLoc))
        );

        for (Rider r : allRiders) {
            if (r.available) pq.add(r); // only available riders go in
        }

        if (pq.isEmpty()) return null;

        Rider best = pq.poll();   // poll() removes + returns the closest rider
        best.available = false;   // mark as busy (on delivery)
        return best;
    }

    // ============================================================
    //  GLOBAL DATA STORES
    // ============================================================

    static CustomerList   customerList   = new CustomerList();
    static RestaurantList restaurantList = new RestaurantList();
    static List<Rider>    allRiders      = new ArrayList<>();

    // Queue: confirmed orders waiting to be processed (FIFO)
    // First order placed = first order cooked
    static Queue<Order>    orderQueue  = new LinkedList<>();

    // Stack: items the admin is currently adding before confirming
    // push() to add an item, pop() to undo the last one (LIFO)
    static Stack<OrderItem> buildStack = new Stack<>();

    // HashMap: instant O(1) lookup by ID — no looping needed
    static HashMap<String, Customer> customerMap = new HashMap<>();
    static HashMap<String, Order>    orderMap    = new HashMap<>();

    static int orderCounter = 1; // auto-increments to generate ORD001, ORD002, ...

    // ============================================================
    //  SAMPLE DATA
    // ============================================================

    static void loadSampleData() {
        // --- Customers at different locations ---
        Customer[] cs = {
            new Customer("C001", "Alice Tan",    "012-3456789", 6), // Northpark
            new Customer("C002", "Bob Lim",      "011-9876543", 5), // Eastview
            new Customer("C003", "Charlie Wong", "013-5551234", 2), // West Market
        };
        for (Customer c : cs) {
            customerList.add(c);
            customerMap.put(c.id, c); // also store in HashMap for fast lookup
        }

        // --- Restaurants with their own BST menus ---
        Restaurant r1 = new Restaurant("R001", "Pizza Palace", 0); // Restaurant District
        r1.addToMenu("BBQ Chicken Pizza",  22.00);
        r1.addToMenu("Garlic Bread",        5.00);
        r1.addToMenu("Margherita Pizza",   18.00);
        r1.addToMenu("Pasta Carbonara",    16.00);
        r1.addToMenu("Tiramisu",            9.00);

        Restaurant r2 = new Restaurant("R002", "Burger Barn", 0); // Restaurant District
        r2.addToMenu("Chicken Sandwich",   14.00);
        r2.addToMenu("Classic Burger",     12.00);
        r2.addToMenu("Double Smash",       18.00);
        r2.addToMenu("Fries",               5.00);
        r2.addToMenu("Milkshake",           8.00);

        Restaurant r3 = new Restaurant("R003", "Sushi Star", 1); // East Junction
        r3.addToMenu("California Roll",    18.00);
        r3.addToMenu("Ebi Tempura",        20.00);
        r3.addToMenu("Matcha Ice Cream",   10.00);
        r3.addToMenu("Miso Soup",           6.00);
        r3.addToMenu("Salmon Sashimi",     25.00);

        restaurantList.add(r1);
        restaurantList.add(r2);
        restaurantList.add(r3);

        // --- Riders spread across the city ---
        allRiders.add(new Rider("D001", "Ali Hassan",  "014-1111111", 3)); // City Center
        allRiders.add(new Rider("D002", "Ben Ooi",     "015-2222222", 0)); // Restaurant District
        allRiders.add(new Rider("D003", "Carmen Lim",  "016-3333333", 1)); // East Junction
        allRiders.add(new Rider("D004", "David Singh", "017-4444444", 2)); // West Market

        System.out.println("   " + customerList.size + " customers");
        System.out.println("   3 restaurants (with BST menus)");
        System.out.println("   " + allRiders.size() + " delivery riders");
        System.out.println("   City map with " + N + " locations ready");
    }

    // ============================================================
    //  MAIN — System Entry Point
    // ============================================================

    public static void main(String[] args) {
        initCity();

        System.out.println("  GoodTech Smart Food Delivery System     ");
        System.out.println("  Admin Control Panel                     ");
        System.out.println("  Initializing system...");
        loadSampleData();

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("                  MAIN MENU                   ");
            System.out.println("  1. Customer Management    [Linked List]      ");
            System.out.println("  2. Restaurant Management  [Linked List + BST]");
            System.out.println("  3. Rider Management       [Priority Queue]   ");
            System.out.println("  4. Place New Order        [Stack + Queue]    ");
            System.out.println("  5. Process Next Order     [Queue + Dijkstra] ");
            System.out.println("  6. Lookup Customer / Order[HashMap]          ");
            System.out.println("  7. City Map & Route Finder[Graph + Dijkstra] ");
            System.out.println("  8. View Pending Order Queue                  ");
            System.out.println("  0. Exit                                      ");
            System.out.print("  Choice: ");

            choice = readInt(sc);
            switch (choice) {
                case 1: menuCustomers(sc);    break;
                case 2: menuRestaurants(sc);  break;
                case 3: menuRiders(sc);       break;
                case 4: placeOrder(sc);       break;
                case 5: processOrder(sc);     break;
                case 6: menuLookup(sc);       break;
                case 7: menuMap(sc);          break;
                case 8: viewQueue();          break;
                case 0: System.out.println("\n  System shutdown. Goodbye!"); break;
                default: System.out.println("  Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    // ============================================================
    //  MENU 1 — CUSTOMER MANAGEMENT (Linked List + HashMap)
    // ============================================================

    static void menuCustomers(Scanner sc) {
        System.out.println("\n Customer Management (Linked List) ");
        System.out.println("  1. View all customers");
        System.out.println("  2. Add new customer");
        System.out.println("  3. Remove customer");
        System.out.print("  Choice: ");

        switch (readInt(sc)) {
            case 1: {
                customerList.display();
                break;
            }
            case 2: {
                System.out.print("  Customer ID (e.g. C004): ");
                String id = sc.nextLine().trim();
                if (customerMap.containsKey(id)) {
                    System.out.println("  ID already taken.");
                    break;
                }
                System.out.print("  Full name: ");
                String name = sc.nextLine().trim();
                System.out.print("  Phone number: ");
                String phone = sc.nextLine().trim();
                printMap();
                System.out.print("  Select customer's home location [0-" + (N-1) + "]: ");
                int loc = readInt(sc);
                if (loc < 0 || loc >= N) { System.out.println("  Invalid location."); break; }
                Customer c = new Customer(id, name, phone, loc);
                customerList.add(c);
                customerMap.put(id, c);
                System.out.println("   Added " + name + " at " + locs[loc].name);
                break;
            }
            case 3: {
                System.out.print("  Enter Customer ID to remove: ");
                String id = sc.nextLine().trim();
                Customer found = customerMap.get(id);
                if (found != null && customerList.remove(id)) {
                    customerMap.remove(id);
                    System.out.println("   Removed: " + found.name);
                } else {
                    System.out.println("  Customer not found.");
                }
                break;
            }
            default: System.out.println("  Invalid option.");
        }
    }

    // ============================================================
    //  MENU 2 — RESTAURANT MANAGEMENT (Linked List + BST)
    // ============================================================

    static void menuRestaurants(Scanner sc) {
        System.out.println("\n Restaurant Management (Linked List + BST Menu) ");
        System.out.println("  1. View all restaurants");
        System.out.println("  2. View a restaurant's menu  (BST inorder A-Z)");
        System.out.println("  3. Search food in a restaurant (BST search)");
        System.out.println("  4. Add new restaurant");
        System.out.println("  5. Add food item to restaurant");
        System.out.println("  6. Remove a restaurant");
        System.out.print("  Choice: ");

        switch (readInt(sc)) {
            case 1: {
                restaurantList.display();
                break;
            }
            case 2: {
                System.out.print("  Restaurant ID: ");
                Restaurant r = restaurantList.findById(sc.nextLine().trim());
                if (r == null) { System.out.println("  Not found."); break; }
                System.out.println("\n  ── " + r.name + " Menu (A-Z, BST inorder) ──");
                int[] cnt = {0};
                bstDisplay(r.menuRoot, cnt);
                if (cnt[0] == 0) System.out.println("  Menu is empty.");
                break;
            }
            case 3: {
                System.out.print("  Restaurant ID: ");
                Restaurant r = restaurantList.findById(sc.nextLine().trim());
                if (r == null) { System.out.println("  Not found."); break; }
                System.out.print("  Food name to search: ");
                FoodItem fi = bstSearch(r.menuRoot, sc.nextLine().trim());
                if (fi != null) System.out.printf("  Found: %s — RM%.2f%n", fi.name, fi.price);
                else            System.out.println("  Not found in this restaurant's menu.");
                break;
            }
            case 4: {
                System.out.print("  Restaurant ID: ");
                String id = sc.nextLine().trim();
                System.out.print("  Restaurant name: ");
                String name = sc.nextLine().trim();
                printMap();
                System.out.print("  Location [0-" + (N-1) + "]: ");
                int loc = readInt(sc);
                if (loc < 0 || loc >= N) { System.out.println("  Invalid location."); break; }
                restaurantList.add(new Restaurant(id, name, loc));
                System.out.println("   Added " + name + " at " + locs[loc].name);
                break;
            }
            case 5: {
                System.out.print("  Restaurant ID: ");
                Restaurant r = restaurantList.findById(sc.nextLine().trim());
                if (r == null) { System.out.println("  Not found."); break; }
                System.out.print("  Food name: ");
                String fname = sc.nextLine().trim();
                System.out.print("  Price (RM): ");
                double price = readDouble(sc);
                r.addToMenu(fname, price);
                System.out.println("   Added '" + fname + "' to " + r.name + "'s menu.");
                break;
            }
            case 6: {
                System.out.print("  Restaurant ID to remove: ");
                String id = sc.nextLine().trim();
                if (restaurantList.remove(id)) System.out.println("   Removed.");
                else                           System.out.println("  Not found.");
                break;
            }
            default: System.out.println("  Invalid option.");
        }
    }

    // ============================================================
    //  MENU 3 — RIDER MANAGEMENT (Priority Queue demonstration)
    // ============================================================

    static void menuRiders(Scanner sc) {
        System.out.println("\n Rider Management (Priority Queue / Min Heap) ");
        System.out.println("  1. View all riders");
        System.out.println("  2. Add new rider");
        System.out.println("  3. Simulate: show priority order to a restaurant");
        System.out.print("  Choice: ");

        switch (readInt(sc)) {
            case 1: {
                if (allRiders.isEmpty()) { System.out.println("  No riders."); break; }
                System.out.printf("\n  %-6s %-14s %-13s %-26s %-10s %s%n",
                    "ID","Name","Phone","Current Location","Coords","Status");
                for (Rider r : allRiders) {
                    System.out.printf("  %-6s %-14s %-13s %-26s (%d,%-2d)  %s%n",
                        r.id, r.name, r.phone, locs[r.locIdx].name,
                        locs[r.locIdx].x, locs[r.locIdx].y,
                        r.available ? "Available" : "On Delivery");
                }
                break;
            }
            case 2: {
                System.out.print("  Rider ID: ");
                String id = sc.nextLine().trim();
                System.out.print("  Name: ");
                String name = sc.nextLine().trim();
                System.out.print("  Phone: ");
                String phone = sc.nextLine().trim();
                printMap();
                System.out.print("  Current location [0-" + (N-1) + "]: ");
                int loc = readInt(sc);
                if (loc < 0 || loc >= N) { System.out.println("  Invalid."); break; }
                allRiders.add(new Rider(id, name, phone, loc));
                System.out.println("   Rider added at " + locs[loc].name);
                break;
            }
            case 3: {   
                // Show how the PriorityQueue would rank all riders for a given restaurant
                restaurantList.display();
                System.out.print("  Enter Restaurant ID: ");
                Restaurant r = restaurantList.findById(sc.nextLine().trim());
                if (r == null) { System.out.println("  Not found."); break; }

                PriorityQueue<Rider> pq = new PriorityQueue<>(
                    Comparator.comparingDouble(rd -> calcDist(rd.locIdx, r.locIdx))
                );
                for (Rider rd : allRiders) if (rd.available) pq.add(rd);

                if (pq.isEmpty()) { System.out.println("  No available riders."); break; }
                System.out.println("\n  Rider priority order for " + r.name
                    + " (nearest first — Min Heap):");
                int rank = 1;
                while (!pq.isEmpty()) {
                    Rider rd = pq.poll();
                    System.out.printf("  %d. %-14s at %-24s → %.1f km away%n",
                        rank++, rd.name, locs[rd.locIdx].name,
                        calcDist(rd.locIdx, r.locIdx));
                }
                break;
            }
            default: System.out.println("  Invalid option.");
        }
    }

    // ============================================================
    //  MENU 4 — PLACE NEW ORDER (Stack for building, Queue for submit)
    //
    //  Stack (LIFO): admin pushes items in as customer decides.
    //    - [A]dd item → push to stack
    //    - [U]ndo     → pop from stack (removes last item added)
    //
    //  Queue (FIFO): once confirmed, order joins the back of the
    //  queue and waits its turn behind earlier orders.
    // ============================================================

    static void placeOrder(Scanner sc) {
        System.out.println("\n Place New Order (Stack + Queue) ");

        // Step 1: Identify the customer
        customerList.display();
        System.out.print("\n  Enter Customer ID: ");
        String cid = sc.nextLine().trim();
        Customer customer = customerMap.get(cid); // O(1) HashMap lookup
        if (customer == null) { System.out.println("  Customer not found."); return; }
        System.out.println("  Customer : " + customer.name);
        System.out.println("  Deliver to: " + customer.locName()
            + " (" + locs[customer.locIdx].x + ", " + locs[customer.locIdx].y + ")");

        // Step 2: Choose restaurant
        restaurantList.display();
        System.out.print("\n  Enter Restaurant ID: ");
        Restaurant restaurant = restaurantList.findById(sc.nextLine().trim());
        if (restaurant == null) { System.out.println("  Restaurant not found."); return; }
        System.out.println("  Restaurant: " + restaurant.name + " at " + restaurant.locName());

        // Step 3: Build the order using Stack
        buildStack.clear();
        boolean building = true;

        while (building) {
            // Show the restaurant menu (BST inorder = A-Z)
            System.out.println("\n   " + restaurant.name + " Menu ");
            int[] cnt = {0};
            bstDisplay(restaurant.menuRoot, cnt);

            // Show what is currently in the stack (the order so far)
            System.out.println("\n  Current order (Stack):");
            if (buildStack.isEmpty()) {
                System.out.println("    (empty)");
            } else {
                double running = 0;
                for (int i = 0; i < buildStack.size(); i++) {
                    OrderItem oi = buildStack.get(i);
                    System.out.println("    " + (i + 1) + ". " + oi);
                    running += oi.price * oi.qty;
                }
                System.out.printf("    Running total: RM%.2f%n", running);
            }

            System.out.println("\n  [A] Add item    [U] Undo last item    [C] Confirm    [X] Cancel");
            System.out.print("  Action: ");
            String action = sc.nextLine().trim().toUpperCase();

            switch (action) {
                case "A": {
                    System.out.print("  Food name: ");
                    String fname = sc.nextLine().trim();
                    FoodItem food = bstSearch(restaurant.menuRoot, fname);
                    if (food == null) {
                        System.out.println("  Not found in menu. Check the spelling.");
                        break;
                    }
                    System.out.print("  Quantity: ");
                    int qty = readInt(sc);
                    if (qty <= 0) { System.out.println("  Quantity must be at least 1."); break; }
                    buildStack.push(new OrderItem(food.name, food.price, qty));
                    System.out.printf("  Pushed to stack: %s x%d (RM%.2f)%n",
                        food.name, qty, food.price * qty);
                    break;
                }
                case "U": {
                    if (buildStack.isEmpty()) {
                        System.out.println("  Stack is empty — nothing to undo.");
                    } else {
                        OrderItem undone = buildStack.pop(); // LIFO — removes the last pushed item
                        System.out.println("  Popped from stack (undone): " + undone.foodName + " x" + undone.qty);
                    }
                    break;
                }
                case "C": {
                    if (buildStack.isEmpty()) {
                        System.out.println("  Nothing in the order. Add at least one item.");
                        break;
                    }
                    // Generate order ID and create the Order object
                    String oid = String.format("ORD%03d", orderCounter++);
                    Order order = new Order(oid, customer, restaurant, new ArrayList<>(buildStack));

                    orderQueue.add(order);    // add to back of FIFO queue
                    orderMap.put(oid, order); // store in HashMap for fast future lookup

                    buildStack.clear();
                    System.out.println("\n   Order confirmed and added to queue!");
                    order.display();
                    System.out.println("  Position in queue: " + orderQueue.size());
                    building = false;
                    break;
                }
                case "X": {
                    buildStack.clear();
                    System.out.println("  Order cancelled. Stack cleared.");
                    building = false;
                    break;
                }
                default:
                    System.out.println("  Invalid. Use A, U, C, or X.");
            }
        }
    }

    // ============================================================
    //  MENU 5 — PROCESS NEXT ORDER (Queue → PriorityQueue → Dijkstra)
    //
    //  1. Poll from Queue (FIFO — oldest order goes first)
    //  2. Build a PriorityQueue of available riders sorted by
    //     distance to the restaurant → assign the nearest
    //  3. Run Dijkstra from restaurant to customer's location
    //     to find the shortest delivery route
    // ============================================================

    static void processOrder(Scanner sc) {
        System.out.println("\n Process Next Order ");

        if (orderQueue.isEmpty()) {
            System.out.println("  No orders in the queue.");
            return;
        }

        // Take the FIRST order from the queue (FIFO)
        Order order = orderQueue.poll();
        order.status = "PROCESSING";

        System.out.println("  Pulled from front of queue:");
        order.display();

        // Find the nearest rider to the restaurant using Priority Queue
        System.out.println("\n  Searching for nearest rider to "
            + order.restaurant.name + "...");

        Rider rider = assignNearestRider(order.restaurant.locIdx);

        if (rider == null) {
            System.out.println("  No available riders! Putting order back in queue.");
            order.status = "QUEUED";
            orderQueue.add(order);
            return;
        }

        double riderDist = calcDist(rider.locIdx, order.restaurant.locIdx);
        System.out.printf("   Nearest rider (Min Heap): %s%n", rider.name);
        System.out.printf("    At %s (%.1f km from restaurant)%n",
            locs[rider.locIdx].name, riderDist);

        order.assignedRider = rider.name;
        order.status = "RIDER ASSIGNED";

        // Run Dijkstra for the delivery route
        System.out.println("\n  Calculating shortest delivery route (Dijkstra)...");
        System.out.printf("  From : %s (%d, %d)%n",
            order.restaurant.locName(),
            locs[order.restaurant.locIdx].x,
            locs[order.restaurant.locIdx].y);
        System.out.printf("  To   : %s (%d, %d)%n",
            order.customer.locName(),
            locs[order.customer.locIdx].x,
            locs[order.customer.locIdx].y);

        dijkstra(order.restaurant.locIdx, order.customer.locIdx);

        System.out.println("\n  Order status: " + order.status);
        System.out.println("  " + rider.name + " is on the way to " + order.customer.name + "!");
    }

    // ============================================================
    //  MENU 6 — LOOKUP (HashMap O(1))
    // ============================================================

    static void menuLookup(Scanner sc) {
        System.out.println("\n Data Lookup (HashMap — O(1) access) ");
        System.out.println("  1. Look up customer by ID");
        System.out.println("  2. Look up order by ID");
        System.out.print("  Choice: ");

        switch (readInt(sc)) {
            case 1: {
                System.out.print("  Customer ID: ");
                Customer c = customerMap.get(sc.nextLine().trim());
                if (c == null) System.out.println("  Not found.");
                else {
                    System.out.println("  ID       : " + c.id);
                    System.out.println("  Name     : " + c.name);
                    System.out.println("  Phone    : " + c.phone);
                    System.out.println("  Location : " + c.locName()
                        + " (" + locs[c.locIdx].x + ", " + locs[c.locIdx].y + ")");
                }
                break;
            }
            case 2: {
                System.out.print("  Order ID (e.g. ORD001): ");
                Order o = orderMap.get(sc.nextLine().trim());
                if (o == null) System.out.println("  Not found.");
                else o.display();
                break;
            }
            default: System.out.println("  Invalid option.");
        }
    }

    // ============================================================
    //  MENU 7 — CITY MAP & ROUTE FINDER (Graph + Dijkstra)
    // ============================================================

    static void menuMap(Scanner sc) {
        System.out.println("\n City Map & Route Finder (Graph + Dijkstra) ");
        printMap();

        System.out.println("\n  Road connections:");
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (roads[i][j] > 0) {
                    System.out.printf("  [%d] %-26s ->  [%d] %-26s %.1f km%n",
                        i, locs[i].name, j, locs[j].name, roads[i][j]);
                }
            }
        }

        System.out.println("\n  Find shortest path between two locations:");
        System.out.print("  Start [0-" + (N-1) + "]: ");
        int start = readInt(sc);
        System.out.print("  End   [0-" + (N-1) + "]: ");
        int end = readInt(sc);

        if (start < 0 || start >= N || end < 0 || end >= N) {
            System.out.println("  Invalid location number.");
            return;
        }
        dijkstra(start, end);
    }

    // ============================================================
    //  MENU 8 — VIEW ORDER QUEUE
    // ============================================================

    static void viewQueue() {
        System.out.println("\n Pending Order Queue (FIFO)");
        if (orderQueue.isEmpty()) {
            System.out.println("  Queue is empty.");
            return;
        }
        System.out.println("  " + orderQueue.size() + " order(s) waiting:\n");
        int pos = 1;
        for 
    (Order o : orderQueue) {
            System.out.printf("  [%d] %-8s  %-16s -> %-20s  RM%6.2f  [%s]%n",
                pos++, o.orderId, o.customer.name,
                o.restaurant.name, o.total, o.status);
        }
    }

    // ============================================================
    //  UTILITY HELPERS
    // ============================================================

    // Read an integer from a full line — avoids Scanner newline issues
    static int readInt(Scanner sc) {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

    static double readDouble(Scanner sc) {
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }
}