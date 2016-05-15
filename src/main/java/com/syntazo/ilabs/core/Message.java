package com.syntazo.ilabs.core;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Message - Map of Attribute Values with Attribute<T> as the Key.
 */
public final class Message implements Cloneable, Serializable {
// ------------------------------ FIELDS ------------------------------

    private final HashMap<Attribute, Object> delegate;
    private static final long serialVersionUID = 7286898337467366119L;

    public Message() {
        this.delegate = new HashMap<Attribute, Object>(100);
    }

    // ------------------------ CANONICAL METHODS ------------------------

    /**
     * This implementation does not follow JAVA standard. We create a new object instead of
     * calling super.clone(). This will break if Message is subclassed. Since it is declared final,
     * the approach will work.
     *
     * @return a clone of this instance.
     * @see Object#clone()
     */
    @SuppressWarnings({"CloneCallsConstructors", "CloneDoesntCallSuperClone"})
    @Override
    public Message clone() {
        Message attributeList = new Message();
        attributeList.delegate.putAll(delegate);
        return attributeList;
    }

    /**
     * @param obj the reference object with which to compare.
     * @return <code>true</code> if this object is the same as the obj
     *         argument; <code>false</code> otherwise.
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message that = (Message) obj;
        return delegate.equals(that.delegate);
    }

    /**
     * @return a hash code value for this object.
     * @see Object#hashCode()
     */
    public int hashCode() {
        return delegate.hashCode();
    }

    /**
     * @return a string representation of the object.
     * @see java.util.AbstractMap#toString()
     */
    public String toString() {
        return delegate.toString();
    }

// -------------------------- OTHER METHODS --------------------------

    /**
     * Removes all of the mappings from this list.
     * The map will be empty after this call returns.
     */
    public void clear() {
        delegate.clear();
    }

    /**
     * Get value associated with key.
     *
     * @param key an attribute
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     * @throws java.lang.ClassCastException exception will be thrown with value stored in the list for the key does
     *                                      not match generic type of the attribute.
     */
    public <T> T get(Attribute<T> key) {
        return (T) delegate.get(key);
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    /**
     * Associate specified value with this attribute.
     *
     * @param key  an attribute
     * @param data some value
     * @return previuos value assocciated with the key
     */
    public <T> T put(Attribute<T> key, T data) {
        return (T) delegate.put(key, data);
    }

    /**
     * Returns the number of attribute-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of attribute-value mappings in this map
     */
    public int size() {
        return delegate.size();
    }
}
