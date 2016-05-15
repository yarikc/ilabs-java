/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

import java.util.Collection;


public interface RoutingStrategy {

	Collection<Node> getRoutesForMessage(Message message, RouteTable table);

	Collection<Node> getRouteFromAgent(Envelope agent, RouteTable table);

	Object[] criteriaFromMessage(Message message);
}
