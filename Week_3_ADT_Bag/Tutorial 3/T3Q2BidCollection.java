public interface T3Q2BidCollection {

    void add(T3Q2BidInterface newBid);
    T3Q2BidInterface getBestYearlyCost();
    T3Q2BidInterface getBestInitialCost();
    void clear();
    int size();
    boolean isEmpty();
}
