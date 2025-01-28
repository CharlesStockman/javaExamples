package lob.org.banking.prepare.workflow.commands;

import lob.org.banking.prepare.LimitOrderBook;

/**
 * List of the actions that can performed on an LimitOrderBook
 *
 * At some future point this can be used for an action in a workflow.
 *
 * @author Charles Stockman
 * @since Java 17
 */
public enum LimitOrderBookActionEnum {
    ADD("add"), REMOVE("remove");

    private String actionName;
    LimitOrderBookActionEnum(String name) {
        this.actionName = name;
    }
}
