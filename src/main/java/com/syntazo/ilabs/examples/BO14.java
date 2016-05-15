package com.syntazo.ilabs.examples;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/1/11
 * Time: 1:34 PM
 */
public class BO14 extends Event {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public BO14(Object source) {
        super(source);
    }

    @Override
    public void accept(EventVisitor visitor) {
        visitor.process(this);
    }
}
