/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;


/**
 * This is a temparary storage buffer that should comply with the FIFO contract
 */
public interface BufferStrategy<T> {

    /**
     * Add data to the end of the buffer
     *
     * @param agent - any data extending Envelope
     * @throws InterruptedException
     */
    void put(T agent);

    /**
     * Get oldest element form the buffer. This method should be block reading thread until data is available
     *
     * @return DeliveryAgent
     * @throws InterruptedException see Thread#wait()
     */
    T take() throws InterruptedException;

	 /**
     * Checks the state of the buffer.
     *
     * @return true if buffer is empty, false ortherwise
     */
    boolean isEmpty();

    /**
     * Returns number of elements in the buffer
     *
     * @return number of elements
     */
    int size();


}
