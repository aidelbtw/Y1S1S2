public class ComparableBook implements Comparable<ComparableBook>{
    private int BookID;
    private String BookName;
     public ComparableBook(int id, String name) {
        this.BookID = id;
        this.BookName = name;
    }
    public int getId() {
        return BookID;
    }
    public void setId(int id) {
        this.BookID = id;
    }
    public String getName() {
        return BookName;
    }
    public void setName(String name) {
        this.BookName = name;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ComparableBook)) {
            return false;
        }
        ComparableBook p = (ComparableBook) o;
        if (this.BookID == p.getId()) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.BookID;
    }
    @Override
    public String toString() {
        return "(" + BookID + ", " + BookName + ")";
         }
    @Override
    public int compareTo(ComparableBook cp) {
        int cpId = cp.getId();
        String cpName = cp.getName();
        if (this.getId() < cpId) {
            return -1;
        }
        if (this.getId() > cpId) {
            return 1;
        }
        if (this.getId() == cpId) {
            return this.getName().compareTo(cpName);
        }
        return 0;
    }
}