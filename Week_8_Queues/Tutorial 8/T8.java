public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        int[] arr = {4, 8, 1, 2, 9, 6, 3, 7};
        q.add(4);
        q.add(8);
        q.add(1);
        q.add(2);
        q.add(9);
        q.add(6);
        q.add(3);
        q.add(7);
        System.out.println(q.toString());
        System.out.println("First element (and remove): " + q.poll());
        Object[] array = q.toArray();
        for (int i = 0; i<array.length; i++)
        {
            System.out.println(array[i]);
        }
        System.out.println("First element (and NOT remove): " + q.peek());
        System.out.println("Element 1 is inside the PQ: " + q.contains(1));
        System.out.println("Size of the PQ: " + q.size());
        while(!q.isEmpty())
        {
            System.out.println("Current element to be removed: " + q.poll());
            System.out.println("Remaining element(s): " + q.toString());
        }
    }