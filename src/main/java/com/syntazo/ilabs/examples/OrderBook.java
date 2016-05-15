package com.syntazo.ilabs.examples;

import com.syntazo.ilabs.core.Attribute;
import com.syntazo.ilabs.core.Message;
import com.syntazo.ilabs.core.Node;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/1/11
 * Time: 1:47 PM
 */
public class OrderBook extends Node {
    public OrderBook(String name) {
        super(name);
    }

    private EventVisitor visitor = new EventVisitor() {
        @Override
        public void process(BO5 event) {
            handleBO5(event);
        }
    };

    @Override
    protected Message processData(Message message) {
        message.get(Attribute.EVENT).accept(visitor);
        return message;
    }

    public void handleBO5(BO5 event) {
        System.out.println("Handling BO5 message");
    }

}
