package lob.org.banking.prepare.workflow.commands;

import lob.org.banking.prepare.Order;
import lob.org.banking.prepare.workflow.CommandInterface;

import java.util.List;

/**
 * Removes an order a list
 *
 * @author Charles Stockman
 * @since Java 17
 */
public class DelOrderCommand implements CommandInterface {

    private final Order order;
    private final List<Order> orders;

    /**
     * An instance of AddOrderCommand
     *
     * @param order  The order that will be added to a list
     * @param orders The list the order is added to
     */
    public DelOrderCommand(Order order, List<Order> orders) {
        this.order = order;
        this.orders = orders;
    }

    /**
     * Removes an order from the list
     */
    public void command() {
        synchronized (this) {
            orders.remove(order);
        }
    }
}
