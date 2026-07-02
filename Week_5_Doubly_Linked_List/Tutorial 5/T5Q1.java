//1a. Lines 10-13: is the scenario when index is 0, put the head at temp, which allows head to be replaced with e
//    Meaning when you return temp.element, it returns the temp element which was the original head element
//
//    Lines 15-21: is the scenario when it is not the last index or more, if will iterate through the index stated and will loop
//    until just before the target, which will be put in temp, thus you are able to switch it out with new element
//    and you can then return the original element that you put in temp
//
//1b. It is used to replace an element at a specified index, and if its over the size, it will be added to the end of the list
//
//1c.   public E xyz(int index, E e){
//      if(index < 0) return null;
//
//      if(index >=  size-1){
//          this.addLast(e);
//          return null;
//      }
//      Node<E> current = head;
//      
//      for (int i = 0 ; i < index ; i++){
//          current = current.next;
//      }
//
//      E oldElement = current.element;
//      current.element = e;
//      return oldElement;
//      }      
//
//
//2a. It is used in a doubly linked list, the code is used to remove an element at an index
//    First we link the element after the current with the one before, and the elemt before
//    the current with the one after, we then de-link the current temp element with anything