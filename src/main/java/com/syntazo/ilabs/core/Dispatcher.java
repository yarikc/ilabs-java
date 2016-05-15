package com.syntazo.ilabs.core;

/**
 * Created by IntelliJ IDEA.
 * User: ychinskiy
 * Date: Sep 7, 2007
 * Time: 1:27:42 PM
 */
public class Dispatcher implements Runnable  {
// ------------------------------ FIELDS ------------------------------

	protected final QueuedBufferStrategy<Task> strategy = new QueuedBufferStrategy<Task>();

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Runnable ---------------------

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				strategy.take().doWork();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

// -------------------------- OTHER METHODS --------------------------

	public void process(final Node source, final Node destination, final Envelope agent) {
		strategy.put(getTask(source, destination, agent));
	}

	protected Task getTask(Node source, Node dest, Envelope agent) {
		return new Task(source, dest, agent);
	}

// -------------------------- INNER CLASSES --------------------------

	protected static class Task {
// ------------------------------ FIELDS ------------------------------

		final Node source;
		final Node destination;
		final Envelope data;

// --------------------------- CONSTRUCTORS ---------------------------

		Task(Node source, Node destination, Envelope data) {
			if (source == null || data == null || destination == null)
				throw new NullPointerException("One of the parameters is null");
			this.source = source;
			this.destination = destination;
			this.data = data;
		}

// -------------------------- OTHER METHODS --------------------------

		void doWork() {
			destination.receive(data);
		}
	}
}
