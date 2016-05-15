/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

import java.util.HashSet;
import java.util.Set;

public class Distributor extends Node {
// ------------------------------ FIELDS ------------------------------

    private final Set<Node> connectedNodes;

// --------------------------- CONSTRUCTORS ---------------------------

    public Distributor(String name) {
        super(name);
        connectedNodes = new HashSet<Node>();
    }

    public Distributor(String name, Set<Node> storage) {
        this(name);
        if (storage != null) {
            connectedNodes.addAll(storage);
        }
    }

// ------------------------ CANONICAL METHODS ------------------------

    public String getDescription() {
        return super.toString() + "->" + connectedNodes.toString();
    }

// -------------------------- OTHER METHODS --------------------------

    public Node connect(Node node, Object... criteria) {
        validateDestination(node);
        connectedNodes.add(node);
        return this;
    }

    public Node disconnect(Node node, Object... criteria) {
        connectedNodes.remove(node);
        return this;
    }

    public Node send(Envelope agent) {
        for (Node node : connectedNodes) {
            node.receive(agent);
        }
        return this;
    }
}
