import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class L8Q3Stock {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        Queue<Double> shareQueue = new LinkedList<>();
        Queue<Double> priceQueue = new LinkedList<>();

        double totalGainLoss = 0;

        while (true) {
            System.out.print("Enter your query (In format 'Buy / Sell x shares at $y each') : ");
            String line = input.nextLine();

            if (line.trim().isEmpty()){
                break;
            }

            String[] tokens = line.trim().split("\\s+");

            String action = tokens[0].toLowerCase();
            double x = Double.parseDouble(tokens[1]);

            String priceToken = tokens[4].replace("$", "");
            double y = Double.parseDouble(priceToken);

            if (action.equals("buy")){
                System.out.println("Buying now...");
                shareQueue.offer(x);
                priceQueue.offer(y);
            } else if (action.equals("sell")){
                    System.out.println("Selling now...");

                    double remainingToSell = x;
                    boolean hasShares = !shareQueue.isEmpty();

                    while (remainingToSell > 0 && !shareQueue.isEmpty()) {
                        double oldestShares = shareQueue.peek();
                        double oldestPrice = priceQueue.peek();

                        if (oldestShares <= remainingToSell){
                            double gainLoss = oldestShares * (y-oldestPrice);
                            totalGainLoss += gainLoss;
                            System.out.println("Total Capital Gain / Loss: " + totalGainLoss);

                            remainingToSell -= oldestShares;
                            shareQueue.poll();
                            priceQueue.poll();
                        } else {
                            double gainLoss = remainingToSell * (y-oldestPrice);
                            totalGainLoss += gainLoss;
                            System.out.println("TOtal Capital Gain / Loss: " + totalGainLoss);
                        
                            shareQueue.poll();
                            shareQueue.offer(oldestShares - remainingToSell);

                            priceQueue.poll();
                            priceQueue.offer(oldestPrice);

                            Queue<Double> tempShare = new LinkedList<>();
                            Queue<Double> tempPrice = new LinkedList<>();

                            tempShare.offer(oldestShares - remainingToSell);
                            tempPrice.offer(oldestPrice);

                            while (shareQueue.size() > 1) {
                                tempShare.offer(shareQueue.poll());
                                tempPrice.offer(priceQueue.poll());
                            }
                            shareQueue.poll();
                            priceQueue.poll();

                            shareQueue = tempShare;
                            priceQueue = tempPrice;

                            remainingToSell = 0;
                        }
                    }

                if (!hasShares){
                    System.out.println("No shares to sell");
                }
            }
            System.out.println("Queue for Share: Queue: " + shareQueue);
            System.out.println("Queue for Price: Queue: " + priceQueue);
        }
        System.out.println("Final Capital Gain / Loss: " + totalGainLoss);
    }
}
