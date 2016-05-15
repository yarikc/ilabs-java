package com.syntazo.ilabs.core;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: May 11, 2007
 * Time: 11:19:45 AM
 */
public class ControlEnvelope extends Envelope {

    public ControlEnvelope(Message message, Object... subject) {
        super(message, subject);
    }

    public Envelope process(Node node) {
        return node.processControlEnvelope(this);
    }

}
