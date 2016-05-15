/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

public class Proxy extends Node {

    public Node plug(Node delegate) {
        delegate.connect(next);
        super.connect(delegate);
        return this;
    }

    public Node unplug() {
        if (next != null) {
            Node tmp = next;
            this.disconnect(next).connect(tmp.next);
        }
        return this;
    }

}
