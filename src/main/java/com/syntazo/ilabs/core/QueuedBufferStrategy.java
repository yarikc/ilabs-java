package com.syntazo.ilabs.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: Sep 7, 2007
 * Time: 12:48:51 PM
 */
public class QueuedBufferStrategy<T> implements BufferStrategy<T> {
	protected BlockingQueue<T> queue = new LinkedBlockingQueue<T>();

	/**
	 * Add data to the end of the buffer
	 *
	 * @param agent - any data extending Envelope
	 * @throws InterruptedException
	 */
	public void put(final T agent) {
		try {
			queue.put(agent);
		} catch (InterruptedException e) {
			// no exception should be thrown since we did not limit the copacity of the queue
		}
	}

	/**
	 * Get oldest element form the buffer. This method should be block reading thread until data is available
	 *
	 * @return DeliveryAgent
	 * @throws InterruptedException see Thread#wait()
	 */
	public T take() throws InterruptedException {
		return queue.take();
	}

	/**
	 * Checks the state of the buffer.
	 *
	 * @return true if buffer is empty, false ortherwise
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * Returns number of elements in the buffer
	 *
	 * @return number of elements
	 */
	public int size() {
		return queue.size();
	}
}
