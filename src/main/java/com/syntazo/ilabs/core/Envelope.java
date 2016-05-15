/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

public abstract class Envelope {
// ------------------------------ FIELDS ------------------------------

	protected Message message;
	protected final Object subject;

// --------------------------- CONSTRUCTORS ---------------------------

	public Envelope(Message message, Object... subject) {
		this.message = message;
		this.subject = subject;
	}

	public Object getSubject() {
		return subject;
	}

	// --------------------- GETTER / SETTER METHODS ---------------------

	/**
	 * @return Returns the message.
	 */
	public Message getMessage() {
		return message;
	}

// -------------------------- OTHER METHODS --------------------------

	/**
	 * Implements Visitor pattern.
	 *
	 * @param node
	 * @return
	 */
	public abstract Envelope process(Node node);

	/**
	 * @param message The message to set.
	 * @return returns this
	 */
	public Envelope setMessage(Message message) {
		this.message = message;
		return this;
	}
}
