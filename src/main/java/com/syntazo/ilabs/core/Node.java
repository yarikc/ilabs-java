package com.syntazo.ilabs.core;

/**
 * Node is the base class of IntegrationLabs framework.
 *
 * @author IntegrationLabs
 * @version 1.1
 */

public abstract class Node {
// ------------------------------ FIELDS ------------------------------

    public static final String DEFAULT_NAME = "No name";
    protected volatile Node next;
    protected volatile STATE state = STATE.WIRED;
    protected final String name;
    protected final String description;

// --------------------------- CONSTRUCTORS ---------------------------

    protected Node() {
        this(DEFAULT_NAME);
    }

    protected Node(String name) {
        this.name = name;
        description = new StringBuilder(name).
                append("<").
                append(getClass().getName()).
                append("@").append(Integer.toHexString(hashCode())).
                append(">").
                toString();
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    protected Node getNext() {
        return next;
    }

    public String getName() {
        return name;
    }

    /**
     * Check the state of this node. There are to states: OPEN - node is active and can process data CLOSED - node is
     * not active and can no longer process any data. BLOCKED - node is blocked by some operation and may not process
     * data but can process it next time
     *
     * @return state
     */
    public STATE getState() {
        return state;
    }

// -------------------------- OTHER METHODS --------------------------

    /**
     * This method implements one-way connection, and allows connection chaining. Example: IF Node A is connected to
     * Node B, all informaton passed to Node A will be processed (@see Node#process(Object)) and send the chained node
     * (@see Node#send(Envelope))
     *
     * @param destination
     * @param criteria
     * @return this node
     * @throws IllegalArgumentException - if next node is the same existing
     * @throws NullPointerException     - if next node is null
     */
    public Node connect(Node destination, Object... criteria) {
        validateDestination(destination);
        this.next = destination;
        return this;
    }

    protected void validateDestination(Node destination) {
        if (this.equals(destination)) {
            throw new IllegalArgumentException("Destination node is the same as this node.");
        }
        if (destination == null) {
            throw new NullPointerException("Destination node must not be NULL");
        }
    }

    public Node disconnect(Node node, Object... criteria) {
        next = null;
        return this;
    }

    public String getDescription() {
        return new StringBuilder(description).
                append("[").append(state).append("]").
                append("->").append(next == null ? "null" : next.getName())
                .toString();
    }

    /**
     * Each Envelope may contain some control information, it may be processed by the node. By default the method
     * will call process method of this node and process the message encapsulated in the Envelope. This
     * implementation utilzes "Visitor" pattern methodology.
     *
     * @param agent - Envelope
     * @return DeliveryAgent that was processed by the method. The default implementation calles the process method of
     * the node to process the message part of the agent.
     */
    protected Envelope processControlEnvelope(ControlEnvelope envelope) {
        envelope.setMessage(this.processControl(envelope.getMessage()));
        return envelope;
    }

    /**
     * This method presents core business logic implementation of the Node.
     *
     * @param message
     * @return processed information
     */
    protected Message processControl(Message message) {
        return message;
    }

    /**
     * Each Envelope may contain some control information, it may be processed by the node. By default the method
     * will call process method of this node and process the message encapsulated in the Envelope. This
     * implementation utilzes "Visitor" pattern methodoligy.
     *
     * @param data - Envelope
     * @return DeliveryAgent that was processed by the method. The default implementation calles the process method of
     * the node to process the message part of the data.
     */
    protected Envelope processDataEnvelope(DataEnvelope envelope) {
        envelope.setMessage(this.processData(envelope.getMessage()));
        return envelope;
    }

    /**
     * This method presents core business logic implementation of the Node.
     *
     * @param message
     * @return processed information
     */
    protected Message processData(Message message) {
        return message;
    }

    /**
     * This method processes input of the node. No business logic should be present there. If one need to overwrite the
     * method, make sure to include process(Object) and send(DeliveryAget) in new implementation.
     *
     * @param agent - Envelope
     */
    public void receive(Envelope agent) {
        if (agent != null) {
            send(agent.process(this));
        }
    }

    /**
     * This method sends Envelope processed by this Node to the chained node.
     *
     * @param agent
     * @return this.Node
     */
    protected Node send(Envelope agent) {
        if (next != null && !isClosed()) {
            next.receive(agent);
        }

        return this;
    }

    protected boolean isClosed() {
        return state == STATE.CLOSED;
    }

    public Node setState(STATE state) {
        this.state = state;
        return this;
    }

// -------------------------- ENUMERATIONS --------------------------

    public enum STATE {
        WIRED, UNWIRED, CLOSED
    }
}
