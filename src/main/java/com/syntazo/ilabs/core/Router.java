/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

import java.util.Collection;


public class Router extends Node {
// ------------------------------ FIELDS ------------------------------

    private final RouteTable table;
    private final RoutingStrategy strategy;

// --------------------------- CONSTRUCTORS ---------------------------

    public Router(String name, RouteTable table, RoutingStrategy strategy) {
        super(name);

        if (table == null) {
            throw new NullPointerException("Route table is NULL.");
        }

        if (strategy == null) {
            throw new NullPointerException("Routing strategy is NULL.");
        }

        this.strategy = strategy;
        this.table = table;
    }

// ------------------------ CANONICAL METHODS ------------------------

    public String getDescription() {
        return super.getDescription() + "->" + table.toString();
    }

// -------------------------- OTHER METHODS --------------------------


    public Node connect(Node node, Object... criteria) {
        table.addRoute(node, criteria);
        return this;
    }

    /*
          * (non-Javadoc)
          *
          * @see com.ilabs.ei.core.RouteTableInterface#removeRoute(Node node, Object[]
          *      criteria)
          */
    public Node disconnect(Node node, Object... criteria) {
        table.removeRoute(node, criteria);
        return this;
    }

    /*
          * (non-Javadoc)
          *
          * @see com.ilabs.ei.core.Node#send(com.ilabs.ei.core.Envelope)
          */
    protected Node send(Envelope agent) {
        if (!isClosed()) {
            Collection<Node> routes = strategy.getRouteFromAgent(agent, table);
            for (Node route : routes) {
                if (route != null && !route.isClosed()) {
                    route.receive(agent);
                }
            }
        }
        return this;
    }
}
