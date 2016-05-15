package com.syntazo.ilabs.examples;

import com.syntazo.ilabs.core.Attribute;
import com.syntazo.ilabs.core.Message;
import com.syntazo.ilabs.core.Node;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/1/11
 * Time: 1:46 PM
 */
public class MarketPlace extends Node {
    public MarketPlace(String name) {
        super(name);
    }

    private EventVisitor visitor = new EventVisitor() {
        @Override
        public void process(BO36 event) {
            handleBO36(event);
        }

        @Override
        public void process(BO14 event) {
            handleBO14(event);
        }
    };

    @Override
    protected Message processData(Message message) {
        message.get(Attribute.EVENT).accept(visitor);
        return message;
    }

    public void handleBO14(BO14 event) {
        System.out.println("Handling BO14 message");
    }

    public void handleBO36(BO36 event) {
        System.out.println("Handling BO36 message");
    }
}
