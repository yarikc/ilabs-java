package com.syntazo.ilabs.examples;

import com.syntazo.ilabs.core.Attribute;
import com.syntazo.ilabs.core.Message;
import com.syntazo.ilabs.core.Node;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/7/11
 * Time: 6:25 PM
 */
public class TradeBook extends Node {
    public TradeBook(String name) {
        super(name);
    }

    private EventVisitor visitor = new EventVisitor() {
        @Override
        public void process(BD6 event) {
            handleBD6(event);
        }
    };

    @Override
    protected Message processData(Message message) {
        message.get(Attribute.EVENT).accept(visitor);
        return message;
    }

    public void handleBD6(BD6 event) {
        System.out.println("Handling BD6 message");
    }

}
