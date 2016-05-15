/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.adapters;

import com.syntazo.ilabs.core.Buffer;
import com.syntazo.ilabs.core.Envelope;

public class BufferReader extends ReadAdapter<Buffer> {

    public BufferReader(Buffer source, String name) {
        super(source, name);
    }

	protected Envelope read() throws InterruptedException {
		return source.read();
	}

	protected void handleInterrupt(final InterruptedException e) {
		// ignore this exception
	}


}
