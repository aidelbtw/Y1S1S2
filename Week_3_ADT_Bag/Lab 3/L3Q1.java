public class L3Q1<T> implements BagInterface<T> {
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int numberofEntries;
    
    public L3Q1(){
        this(DEFAULT_CAPACITY);
    }

    public L3Q1(int capacity){
        bag = (T[]) new Object[capacity];   
    }

    public int getCurrentSize(){
        return numberofEntries;
    }

    public boolean isFull(){
        return numberofEntries == DEFAULT_CAPACITY;
    }

    public boolean isEmpty(){
        return numberofEntries == 0;
    }

    public boolean add(T newEntry){
        if(isFull()){
            return false;
        } else {
            bag[numberofEntries++] = newEntry;
            return true;
        }
    }

    public T remove(){
        T result = removeEntry(numberofEntries-1);
        return result;
    }

    public boolean remove(T anEntry){
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    private T removeEntry(int givenIndex){
        T result = null;
        if(!isEmpty() && (givenIndex>=0)){
            result = bag[givenIndex];
            numberofEntries--;  //it replaces what you take out, with the very last entry

            bag[givenIndex] = bag[numberofEntries];
            bag[numberofEntries] = null;
        }
        return result;
    }

    private int getIndexOf(T anEntry){
        int where = -1;
        boolean found = false;
        for (int index = 0; !found && (index < numberofEntries); index++) {
            if(anEntry.equals(bag[index])){
                found = true;
                where = index;
            }
        }
        return where;
    }

    public void clear(){
        while ((!isEmpty())) {
            remove();
        }
    }

    public int getFrequencyOf(T anEntry){
        int counter = 0;
        for (int index = 0; index < numberofEntries; index++){
            if(anEntry.equals(bag[index])){
                counter++;
            }
        }
        return counter;
    }

    public boolean contains(T anEntry){
        boolean found = false;
        for(int index = 0; !found && (index < numberofEntries); index++){
            if (anEntry.equals(bag[index])){
                found = true;
            }
        }
        return found;
    }

    public T[] toArray(){
        T[] result = (T[]) new Object[numberofEntries];
        for (int index = 0; index < numberofEntries; index++){
            result[index] = bag[index];
        }
        return result;
    }


    public BagInterface<T> union(BagInterface<T> anotherBag){
        int sizeOfUnionBag = anotherBag.getCurrentSize() + getCurrentSize();
        BagInterface<T> unionbag = new L3Q1<T>(sizeOfUnionBag);
        L3Q1<T> otherBag = (L3Q1<T>) anotherBag;
        int index;

        for (index = 0; index < numberofEntries; index++){
            unionbag.add(bag[index]);
        }

        for (index = 0; index < otherBag.getCurrentSize(); index++){
            unionbag.add(otherBag.bag[index]);
        }
        return unionbag;
    }


    public BagInterface<T> intersection(BagInterface<T> anotherBag){
        BagInterface<T> intersectionBag = new L3Q1<T>();
        L3Q1<T> otherBag = (L3Q1<T>) anotherBag;
        BagInterface<T> copyOfAnotherBag = new L3Q1<T>();
        int index;

        for (index = 0; index < otherBag.numberofEntries; index++){
            copyOfAnotherBag.add(otherBag.bag[index]);
        }

        for (index = 0; index < getCurrentSize(); index++){
            if (copyOfAnotherBag.contains(bag[index])){
                intersectionBag.add(bag[index]);
                copyOfAnotherBag.remove(bag[index]);
            }
        }
        return intersectionBag;
    }


    public BagInterface<T> difference(BagInterface<T> anotherBag){
        BagInterface<T> differenceBag = new L3Q1<T>();
        L3Q1<T> otherBag = (L3Q1<T>) anotherBag;
        int index;
        
        for (index = 0; index < otherBag.numberofEntries; index++){
            differenceBag.add(otherBag.bag[index]);
        }

        for (index = 0; index < getCurrentSize(); index++){
            if (differenceBag.contains(otherBag.bag[index])){
                differenceBag.remove(otherBag.bag[index]);
            }
        }
        return differenceBag;
    }
}