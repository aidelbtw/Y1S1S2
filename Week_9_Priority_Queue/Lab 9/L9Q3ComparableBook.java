public class L9Q3ComparableBook implements Comparable<L9Q3ComparableBook> {
    private int bookId;
    private String title;

    public L9Q3ComparableBook(int bookId, String title){
        this.bookId = bookId;
        this.title = title;
    }

    public int getBookId(){
        return bookId;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public int compareTo(L9Q3ComparableBook other){
        return Integer.compare(bookId, other.bookId);
    }

    @Override
    public String toString(){
        return "[Book ID: " +  bookId + ", Title: " + title +"]";
    }
}
