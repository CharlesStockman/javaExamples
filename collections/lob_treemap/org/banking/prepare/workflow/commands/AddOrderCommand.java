package lob.org.banking.prepare.workflow.commands;

import lob.org.banking.prepare.Order;
import lob.org.banking.prepare.workflow.CommandInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Adds an order a list
 *
 * @author Charles Stockman
 * @since Java 17
 */
public class AddOrderCommand implements CommandInterface {
    private final Order order;
    private final TreeMap<Double, List<Order>> orders;

    /**
     * An instance of AddOrderCommand
     *
     * @param order  The order that will be added to a list
     * @param orders The list the order is added to
     */
    public AddOrderCommand(Order order, TreeMap<Double, List<Order>> orders) {
        this.order = order;
        this.orders = orders;
    }

    /**
     * Adds an order to a list
     */
    public void command() {
            List<Order> ordersForKey  = orders.getOrDefault(order.priceToBeSoldAt(), new ArrayList<>());
            ordersForKey.add(order);
            orders.put(order.priceToBeSoldAt(), ordersForKey);
    }
}
