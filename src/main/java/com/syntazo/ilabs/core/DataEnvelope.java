package com.syntazo.ilabs.core;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: May 18, 2007
 * Time: 12:36:20 PM
 */
public class DataEnvelope extends Envelope {
	public DataEnvelope(Message message, Object... subject) {
		super(message, subject);
	}

	public Envelope process(Node node) {
		return node.processDataEnvelope(this);
	}
}
