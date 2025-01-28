package lob.org.banking.prepare;

import lob.org.banking.prepare.workflow.commands.AddOrderCommand;
import lob.org.banking.prepare.workflow.commands.LimitOrderBookActionEnum;
import lob.org.banking.prepare.workflow.commands.ValidateOrderCommand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
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
    private final List<Order> buyOrders;

    /**
     * A list of sell orders
     */
    private final List<Order> sellOrders;

    /**
     * The symbol that is buy/sell order are for.
     */
    private final String symbol;

    /**
     * Create an empty LimitOrderBook
     * @param symbol -- Three Symbol for the Buy and Sell orders
     */
    public LimitOrderBook(String symbol) {
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        this.symbol = symbol;
    }

    /**
     * Create an LimitOrderBook where a order is added to either the buy or sell list.  Uses the idea of immutability
     * to make the class threadsafe.
     *
     * @param orderBook         The original class with the information
     * @param action            Either buy or sell
     * @param order             A new entry for either the by or sell list
     */
    public LimitOrderBook(LimitOrderBook orderBook, LimitOrderBookActionEnum action, Order order ) {
        this.symbol = orderBook.symbol;
        this.buyOrders = new ArrayList<>(orderBook.buyOrders);
        this.sellOrders = new ArrayList<>(orderBook.sellOrders);
        (new ValidateOrderCommand(order)).command();
        if ( action == LimitOrderBookActionEnum.ADD )
            (new AddOrderCommand(order,this.selectList(order))).command();
    }

    /**
     * Select either the buy list or list based on the Orders action
     *
     * @param order         The order used to identify whether the buy list or sell list is ued
     * @return              The list that will be used for the order.
     */
    private List<Order> selectList(Order order) {
        List<Order> orders = switch(order.action().toLowerCase()) {
            case "buy" -> buyOrders;
            case "sell" -> sellOrders;
            default -> throw new IllegalArgumentException("Order did not contain an acceptable value.  Valid values should be buy/sell");
        };
        return orders;
    }

    /**
     * Display each order in the list and print all fields.
     */
    public void toStringFullyDisplay() {
        LimitOrderBook.DisplayData displayAllFields = this.new DisplayData();
        displayAllFields.displayFull();
    }

    /**
     * Display only the Order's Price to be sold at and quantity
     *
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
         * The comparator for sorting "prices to be sold at" from highest to the lowest value
         */
        public final Comparator<Order> priceComparatorForBuy = Comparator.comparing(Order::priceToBeSoldAt).reversed();

        /**
         * The comparator for sorting "prices to be sold at" from lowest to highest
         */
        public final Comparator<Order> priceComparatorForSell = Comparator.comparing(Order::priceToBeSoldAt);

        /**
         * The template to display the data
         *
         * @param buyOrders         The list of orders that will be brought at a certain price and quantity
         * @param buyDisplay        The routine to print out the data to the user for the buyOrders
         * @param sellOrders        The list of orders that will be sold at a certain price and quantity
         * @param sellDisplay       The routine to print out the data to the user for the sellOrder
         */
        private void toString(List<Order> buyOrders, BiConsumer<List<Order>, Comparator<Order>> buyDisplay, Comparator<Order> buyComparator,
                              List<Order> sellOrders, BiConsumer<List<Order>, Comparator<Order>> sellDisplay, Comparator<Order> sellComparator) {
            System.out.println(buyHeader());
            buyDisplay.accept(buyOrders, buyComparator);
            System.out.println(sellHeader());
            sellDisplay.accept(sellOrders, sellComparator);
        }

        /**
         * Provides the header (buy stocks) for the template (toString)
         *
         * @return The Header for buy stocks
         */
        private String buyHeader () {
            return "Buy Stocks:";
        }

        /**
         * Provides the header (sell stocks) for the template (toString)
         *
         * @return The Header for "sell stocks"ncNl->Nj
         *
         */
        private String sellHeader() {
            return "Sell Stocks:";
        }

        /**
         * Display all the fields of each order
         *
         * @param records   The list of orders that will displayed
         */
        private  void displayOrdersFull(List<Order> records, Comparator<Order> comparator) {
            Consumer<Order> printRecords = System.out::println;
            records.stream().sorted(comparator).forEach(printRecords);

        }

        /**
         * Display the Price and Quantity of each order
         *
         * @param records  The list of orders that will be displayed
         */
        public void displayOrdersPrintAndQuantity(List<Order> records, Comparator<Order> comparator) {
            Consumer<Order> printRecords = order -> System.out.printf("Prices: %.1f, Quantity %d%n", order.priceToBeSoldAt(), order.quantity());
            sellOrders.stream().sorted(comparator).forEach(printRecords);
        }

        /**
         * Instantiates a template so that it will display all the fields for the buy and sell stocks.
         */
        public void displayFull() {
            toString(buyOrders, this::displayOrdersFull, priceComparatorForBuy, sellOrders, this::displayOrdersFull, priceComparatorForSell);
        }

        /**
         * Instantiates a template so that it will display the price and quantity  for the buy and sell stocks.
         */
        public void displayPriceAndQuantity() {
            toString(buyOrders, this::displayOrdersPrintAndQuantity, priceComparatorForBuy, sellOrders, this::displayOrdersPrintAndQuantity, priceComparatorForSell);
        }
    }

    /**
     * Create a Limit Order with buy stocks and sell stocks that will printed out.
     * @param args
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
