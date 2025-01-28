package lob.org.banking.prepare;

import lob.org.banking.prepare.workflow.commands.AddOrderCommand;
import lob.org.banking.prepare.workflow.commands.LimitOrderBookActionEnum;
import lob.org.banking.prepare.workflow.commands.ValidateOrderCommand;

import java.util.*;
import java.util.function.Consumer;

/**
 * A class that imitates a LimitOrderBook for buying/selling quantities of stock
 *
 * @author Charles Stockman
 * @since Java 17
 */
public class LimitOrderBook {

    /**
     * A list of buy orders
     */
    private final TreeMap<Double, List<Order>> buyOrders;


    /**
     * A list of sell orders
     */
    private final TreeMap<Double, List<Order>> sellOrders;


    /**
     * The symbol of the company the operation is for.
     */
    private final String symbol;

    /**
     * Create an empty LimitOrderBook
     * @param symbol -- Three Symbol for the Buy and Sell orders
     */
    public LimitOrderBook(String symbol) {
        buyOrders = new TreeMap<>((double1, double2) -> Double.compare(double2, double1));
        sellOrders = new TreeMap<>();
        this.symbol = symbol;
    }

    /**
     * Create an LimitOrderBook where an order is added to either the buy or sell list.  Uses the idea of immutability
     * to make the class threadsafe.
     *
     * @param orderBook         The original class with the information
     * @param action            Either buy or sell
     * @param order             A new entry for either the by or sell list
     */
     public LimitOrderBook(LimitOrderBook orderBook, LimitOrderBookActionEnum action, Order order ) {
          this.symbol = orderBook.symbol;
          this.buyOrders = new TreeMap<>(orderBook.buyOrders);
          this.sellOrders = new TreeMap<>(orderBook.sellOrders);
          (new ValidateOrderCommand(order)).command();
          if ( action == LimitOrderBookActionEnum.ADD )
             (new AddOrderCommand(order,this.selectList(order))).command();
    }

    /**
     * Select either the buy list or sell list based on the Orders action
     *
     * @param order         The order used to identify whether the buy list or sell list is used
     * @return              The list that will be used for the operation
     */
    private TreeMap<Double, List<Order>> selectList(Order order) {
        TreeMap<Double, List<Order>> orders = switch(order.action().toLowerCase()) {
            case "buy" -> buyOrders;
            case "sell" -> sellOrders;
            default -> throw new IllegalArgumentException("Order did not contain an acceptable value.  Valid values should be buy/sell");
        };
        return orders;
    }

    /**
     * Display each order in the list and print out all fields.
     */
     public void toStringFullyDisplay() {
          LimitOrderBook.DisplayData displayAllFields = this.new DisplayData();
          displayAllFields.displayFull();
      }

    /**
     * Display only the Order's Price to be sold at and quantity
     */
    public void toStringPriceQuantityDisplay() {
        LimitOrderBook.DisplayData displayAllFields = this.new  DisplayData();
        displayAllFields.displayPriceAndQuantity();
    }

    /**
     * An innerclass used to print out the data for the user to analyze
     *
     * @author Chales Stockman
     * @since Java 17
     */
    private class DisplayData {

        /**
         * The template to display the data
         *
         * @param buyOrders         The list of orders that will be brought at a certain price and quantity
         * @param sellOrders        The list of orders that will be sold at a certain price and quantity
         */
        private void toString(TreeMap<Double, List<Order>> buyOrders,  Consumer<TreeMap<Double, List<Order>>> buyDisplay,
                              TreeMap<Double, List<Order>> sellOrders, Consumer<TreeMap<Double, List<Order>>> sellDisplay) {
            System.out.println(buyHeader());
            buyDisplay.accept(buyOrders);
            System.out.println(sellHeader());
            sellDisplay.accept(sellOrders);
        }

        // ************************************************************************************************************/
        /* Print out different section of the template toString() above.  It will print out the buy header, buy       */
        /* seller, the way the data should be displayed for buy orders and the way the data should be displayed       */
        /* sell orders                                                                                                */
        /* The buy and sell section can have a different way of being displayed since the user must supply a function */
        /* for each section.                                                                                          */
        // ************************************************************************************************************/

        /**
         * Provides the header (buy stocks) for the template ( toString )
         *
         * @return The Header for buy stocks
         */
         private String buyHeader () {
            return "Buy Stocks:";
         }

        /**
         * Provides the header (sell stocks) for the template ( toString)
         *
         * @return The Header for "sell stocks"
         */
        private String sellHeader() {
            return "Sell Stocks:";
        }

        /**
         * Display all the fields of each order
         *
         * @param orders   The list where each item will be displayed
         */
        private  void displayOrdersFull(TreeMap<Double, List<Order>> orders) {
            orders.values().stream().forEach(order -> System.out.println(order.toString()));
        }

        /**
         * Display the Price and Quantity of each order
         *
         * @param orders -- The orders that will be displayed and formatted by this
         **/
        public void displayOrdersPriceAndQuantity(TreeMap<Double, List<Order>> orders) {
            orders.values().stream().flatMap(Collection::stream).forEach(
                    order -> System.out.printf("Prices: %.1f, Quantity: %d%n", order.priceToBeSoldAt(), order.quantity()));
        }

        /* ************************************************************************************************************/
        /* This section provides standard function by creating function to fill in the template ( toString )          */
        /* ************************************************************************************************************/

        /**
         * Instantiates a template so that it will display all the fields for the buy and sell stocks.
         */
        public void displayFull() {
              toString(buyOrders, this::displayOrdersFull, sellOrders, this::displayOrdersFull);
        }

        /**
         * Instantiates a template so that it will display the price and quantity  for the buy and sell stocks.
         */
        public void displayPriceAndQuantity() {
            toString(buyOrders, this::displayOrdersPriceAndQuantity, sellOrders, this::displayOrdersPriceAndQuantity);
        }
    }

    /**
     * Create a Limit Order with buy stocks and sell stocks that will be printed out.
     * @param args -- An array of the arguments provided to the program
     */
    public static void main(String... args) {

        LimitOrderBook orderBook = new LimitOrderBook("AAPL");

        Order buyOrder1 = new Order("AAPL", "BUY", 10, 150.00);
        Order buyOrder2 = new Order( "AAPL", "BUY", 15, 149.50);
        Order buyOrder3 = new Order("AAPL", "BUY", 15, 153.10);

        Order sellOrder1 = new Order("AAPL", "SELL", 20, 151.00);
        Order sellOrder2 = new Order( "AAPL", "SELL", 12, 151.50);
        Order sellOrder3 = new Order( "AAPL", "SELL", 12, 149.50);

        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, buyOrder1);
        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, buyOrder2);
        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, buyOrder3);

        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, sellOrder1);
        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, sellOrder2);
        orderBook = new LimitOrderBook(orderBook, LimitOrderBookActionEnum.ADD, sellOrder3);

        orderBook.toStringFullyDisplay();

        orderBook.toStringPriceQuantityDisplay();

    }
}
