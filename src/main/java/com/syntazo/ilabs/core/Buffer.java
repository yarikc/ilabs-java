/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;


public class Buffer extends Node {

	private final BufferStrategy<Envelope> strategy;

	/**
	 * Create new Buffer that can be used to glue two threads together.
	 * Thread1 whould call buffer.receive(agent) to deposit
	 * Tread2 would call buffer.read() to get deposited agent
	 * This allows two threads to run ASYNC while sharing buffer of data.
	 *
	 * @param strategy - buffer strategy.
	 * @throws IllegalArgumentException exception is thrown if strategy is null.
	 */
	public Buffer(BufferStrategy<Envelope> strategy) {
		if (strategy == null) {
			throw new NullPointerException("Strategy is null.");
		}

		this.strategy = strategy;
	}

	/**
	 * Overriden for performance.
	 *
	 * @see Node#connect(Node,java.lang.Object[])
	 */
	@Override
	public Node connect(Node destination, Object... criteria) throws IllegalArgumentException {
		return this;
	}

    @Override
    protected Envelope processDataEnvelope(DataEnvelope data) {
        strategy.put(data);
        return null;
    }

	/**
	 * Read agent from the buffer's strategy. The strategy determines the agent that is returned by
	 * this method.
	 *
	 * @return next available agent form the strategy implementation.
	 * @throws InterruptedException
	 */
	public Envelope read() throws InterruptedException {
		return strategy.take();
	}

}
