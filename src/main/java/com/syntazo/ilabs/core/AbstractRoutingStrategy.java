/*
 * Copyright (c) 2005 IntegrationLabs. All Rights Reserved.
 */
package com.syntazo.ilabs.core;

import java.util.Collection;


public abstract class AbstractRoutingStrategy implements RoutingStrategy {

	public Collection<Node> getRoutesForMessage(Message message, RouteTable table) {
		return table.routes(criteriaFromMessage(message));
	}

	public Collection<Node> getRouteFromAgent(Envelope agent, RouteTable table) {
		return this.getRoutesForMessage(agent.getMessage(), table);
	}

}
