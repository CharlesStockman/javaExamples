package lob.org.banking.prepare.workflow.commands;

import lob.org.banking.prepare.Order;
import lob.org.banking.prepare.workflow.CommandInterface;

import java.util.regex.Pattern;

/**
 * Validates an Order
 *
 * @author Charles Stockman
 * @since Java 17
 */
public class ValidateOrderCommand implements CommandInterface {

    /**
     * The order being validated
     */
    private final Order order;

    /**
     * Creates an instance of ValidateOrderCommand
     * @param order The specific order that will be validated.
     */
    public ValidateOrderCommand(Order order) {
        this.order = order;
    }

    /**
     * A pattern used to validate the symbol
     */
    final Pattern validate_symbol = Pattern.compile("[a-z]{4}", Pattern.CASE_INSENSITIVE);

    /**
     * Validates an order and throws an exception if there is a problem
     */
    public void command() {
        if (order == null)
            throw new IllegalArgumentException("No order was given.");
        else if (order.symbol() == null || order.symbol().length() == 0)
            throw new IllegalArgumentException("The order did not contain a symbol");
        else if (order.quantity() == null)
            throw new IllegalArgumentException("The order did not contain a value");
        else if (order.priceToBeSoldAt() == null)
            throw new IllegalArgumentException("The price to sell does not contain value ");

        if (!(order.action().equalsIgnoreCase("buy") || order.action().equalsIgnoreCase("sell")))
            throw new IllegalArgumentException(String.format("The action must be either buy or sell but (case insensitive) was %s",order.action()));

        boolean symbol_found = validate_symbol.matcher(order.symbol()).find();
        if ( symbol_found == false )
            throw new IllegalArgumentException("The symbol is not three letters");
    }
}
