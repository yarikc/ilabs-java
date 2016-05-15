/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.adapters;

import com.syntazo.ilabs.core.Envelope;
import com.syntazo.ilabs.core.Node;

import java.util.TimerTask;

public abstract class ReadAdapter<T> extends Node implements Runnable {

    protected T source;
    protected volatile TimerTask task;

    public ReadAdapter(T source, String name) throws IllegalArgumentException {
        super(name);
        if (source == null) {
            throw new IllegalArgumentException("Source must not be NULL.");
        }
        this.source = source;
    }

    public void run() {
        while (!isClosed()) {
            try {
                Envelope agent = read();
                if (agent != null)
                    receive(agent);
            } catch (InterruptedException e) {
                handleInterrupt(e);
            }
        }
    }

    protected abstract Envelope read() throws InterruptedException;

    protected abstract void handleInterrupt(InterruptedException e);
}