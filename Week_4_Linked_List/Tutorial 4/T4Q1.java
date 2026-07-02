public class T4Q1 {
    //* a. Node<Character> node1 = new Node<>('a');
    //    Node<Character> node2 = new Node<>('b');
    
    //    Node<Character> head = node1;
    //    Node<Character> tail = node2;

    // e. If empty list, newNode is head and tail
    //    If 1 node, newNode is head and original is tail
    //    If multiple node, newNode is head

    //f.  Create new node with element e
    //    Check condition if empty
    //      IF (head == null){
    //          newNode.next = null;
    //          head = newNode;
    //          tail = newNode;
    //          size = 1;
    //          }
    //      ELSE {
    //          newNode.next = head;    basically to kinda tell that newNode is placed behind head
    //          head = newNode;
    //          size++;
    //          }

    //g.    public void AddFirst(E e){
    //          Node<E> newNode = new Node<>(e);
    //          
    //          IF (head == null){
    //              newNode.next = head;
    //              head = newNode;
    //              tail = newNode;
    //          } ELSE {
    //              newNode.next = head;
    //              head = newNode;
    //          }
    //          size++;


    //h.    public void AddLast(E e){
    //          Node<E> newNode = new Node<>(e);
    //          
    //          IF (head == null){
    //              newNode.next = null;
    //              head = newNode;
    //              tail = newNode;
    //          } ELSE {
    //              tail.next = newNode;
    //              tail = newNode;
    //          }
    //          size++;
    //
    //
    //      public void add(int index, E e) {
    //          IF (index < 0 || index > size)
    //              throw new IndexOutOfBoundsException("Out of bounds");
    //          IF (index == 0) { addFirst(e) return; }
    //          IF (index == size) { addLast(e) return; }
    //
    //          Node<E> current = head;
    //          Node<E> newNode = new Node<>(e);
    //          for (int i = 1 ; i < index ; i++) {
    //              current = current.next;
    //
    //          newNode.next = current.next;
    //          current.next = newNode;
    //          size++;
    //          }
    //
    //
    //      public E removeFirst(){
    //          if (head == null) throw new NoSuchElementException();
    //         
    //          E element = head.element;
    //          head = head.next;
    //          if (head == null) tail = null;
    //          size--;
    //          return element;
    //          }
    //
    //
    //      public E removeLast(){
    //          if (head == null) throw new NoSuchElementException();
    //          
    //          E element = tail.element;
    //          if (head == tail){
    //              head = null;
    //              tail = null;
    //          } else {
    //              Node<e> current = head;
    //              while (current.next != tail){
    //                  current = current.next;
    //              }
    //          current.next = null;
    //          tail = current;
    //          }
    //          size--;
    //          return element;
    //          }
    //
    //
    //      public E remove(int index){
    //          if (index < 0 || index >= size){ 
    //              throw new NoSuchElementException}
    //          if (index == 0 ) return removeFirst();
    //          if (index == size - 1 ) return removeLast();
    //      
    //          Node <E> current = head;
    //          for (int i  = 1; i < index; i++){
    //              current = current.next;
    //          }
    //          
    //          E element = current.next.element;
    //          current.next = current.next.next;
    //          size--;
    //          return element;
    //          }
    

}
