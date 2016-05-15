package com.syntazo.ilabs.core;


import com.syntazo.ilabs.examples.Event;

import java.io.Serializable;

/**
 * This class represents a key for an attribute. The key should be used together with Message
 * to store properties. It uses JAVA generics to define type of the value that the attribute can refer to.
 */
public class Attribute<T> implements Serializable {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 2708920784383418114L;

    public static Attribute<String> EVENT_ID = new Attribute<String>();
    public static Attribute<String> DESCRIPTION = new Attribute<String>();
    public static Attribute<Event> EVENT = new Attribute<Event>();

}
