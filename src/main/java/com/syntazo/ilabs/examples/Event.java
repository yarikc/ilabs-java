package com.syntazo.ilabs.examples;

import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: 4/1/11
 * Time: 1:26 PM
 */
public abstract class Event extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(Object source) {
        super(source);
    }

    public abstract void accept(EventVisitor visitor);
}
