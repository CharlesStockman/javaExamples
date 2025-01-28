package lob.org.banking.prepare.workflow.commands;

import lob.org.banking.prepare.Order;
import lob.org.banking.prepare.workflow.CommandInterface;

import java.util.List;

/**
 * Adds an order a list
 *
 * @author Charles Stockman
 * @since Java 17
 */
public class AddOrderCommand implements CommandInterface {
    private Order order;
    private List<Order> orders;

    /**
     * An instance of AddOrderCommand
     *
     * @param order  The order that will be added to a list
     * @param orders The list the order is added to
     */
    public AddOrderCommand(Order order, List<Order> orders) {
        this.order = order;
        this.orders = orders;
    }

    /**
     * Adds an order to a list
     */
    public void command() {
        synchronized (this) {
            orders.add(order);
        }
    }
}
