/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RouteTable {
// ------------------------------ FIELDS ------------------------------

    protected final Map<Object, Distributor> table;

// --------------------------- CONSTRUCTORS ---------------------------

    public RouteTable() {
        table = buildTable();
    }

    protected Map<Object, Distributor> buildTable() {
        return new ConcurrentHashMap<Object, Distributor>();
    }

// ------------------------ CANONICAL METHODS ------------------------

    public String toString() {
        return table.toString();
    }

// -------------------------- OTHER METHODS --------------------------

    protected Distributor prepareRoute(Object criteria) {
        return new Distributor("Distributor");
    }

    public RouteTable addRoute(Node node, Object... criteria) {
        if (criteria != null && node != null) {
            for (Object _criteria : criteria) {
                if (_criteria != null) {
                    Distributor distributor = table.get(_criteria);
                    if (distributor == null) {
                        distributor = prepareRoute(_criteria);
                        table.put(_criteria, distributor);
                    }
                    distributor.connect(node);
                }
            }
        }
        return this;
    }

    public RouteTable removeRoute(Node node, Object... criteria) {
        if (criteria != null && node != null) {
            for (Object _criteria : criteria) {
                if (_criteria != null) {
                    Distributor distributor = table.get(_criteria);
                    if (distributor != null) {
                        distributor.disconnect(node);
                    }
                }
            }
        }
        return this;
    }

    public Collection<Node> routes(Object... criteria) {
        Set<Node> set = new HashSet<Node>();
        for (Object aCriteria : criteria) {
            Distributor distributor = table.get(aCriteria);
            if (distributor != null) {
                set.add(distributor);
            }
        }
        return set;
    }
}
