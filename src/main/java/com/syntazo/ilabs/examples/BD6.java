package com.syntazo.ilabs.examples;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/1/11
 * Time: 1:44 PM
 */
public class BD6 extends Event {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public BD6(Object source) {
        super(source);
    }

    @Override
    public void accept(EventVisitor visitor) {
        visitor.process(this);
    }
}
