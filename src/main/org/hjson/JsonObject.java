/*******************************************************************************
 * Copyright (c) 2013, 2015 EclipseSource.
 * Copyright (c) 2015-2016 Christian Zangl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.hjson;

import org.hjson.JsonObject.Member;

import java.io.*;
import java.util.*;

/**
 * Represents a JSON object, a set of name/value pairs, where the names are strings and the values
 * are JSON values.
 * <p>
 * Members can be added using the <code>add(String, ...)</code> methods which accept instances of
 * {@link JsonValue}, strings, primitive numbers, and boolean values. To modify certain values of an
 * object, use the <code>set(String, ...)</code> methods. Please note that the <code>add</code>
 * methods are faster than <code>set</code> as they do not search for existing members. On the other
 * hand, the <code>add</code> methods do not prevent adding multiple members with the same name.
 * Duplicate names are discouraged but not prohibited by JSON.
 * </p>
 * <p>
 * Members can be accessed by their name using {@link #get(String)}. A list of all names can be
 * obtained from the method {@link #names()}. This class also supports iterating over the members in
 * document order using an {@link #iterator()} or an enhanced for loop:
 * </p>
 * <pre>
 * for (Member member : jsonObject) {
 *     String name = member.getName();
 *     JsonValue value = member.getValue();
 *     ...
 * }
 * </pre>
 * <p>
 * Even though JSON objects are unordered by definition, instances of this class preserve the order
 * of members to allow processing in document order and to guarantee a predictable output.
 * </p>
 * <p>
 * Note that this class is <strong>not thread-safe</strong>. If multiple threads access a
 * <code>JsonObject</code> instance concurrently, while at least one of these threads modifies the
 * contents of this object, access to the instance must be synchronized externally. Failure to do so
 * may lead to an inconsistent state.
 * </p>
 * <p>
 * This class is <strong>not supposed to be extended</strong> by clients.
 * </p>
 */
@SuppressWarnings("serial") // use default serial UID
public class JsonObject extends JsonValue implements Iterable<Member>{

    private final Map<String, JsonValue> map;

    /**
     * Creates a new empty JsonObject.
     */
    public JsonObject(){
        map = new LinkedHashMap<>();
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified <code>int</code> value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, int value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified <code>long</code> value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, long value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified <code>float</code> value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, float value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified <code>double</code> value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, double value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified <code>boolean</code> value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, boolean value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the JSON
     * representation of the specified string.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, String value){
        add(name, valueOf(value));
        return this;
    }

    /**
     * Appends a new member to the end of this object, with the specified name and the specified JSON
     * value.
     * <p>
     * This method <strong>does not prevent duplicate names</strong>. Calling this method with a name
     * that already exists in the object will append another member with the same name. In order to
     * replace existing members, use the method <code>set(name, value)</code> instead. However,
     * <strong> <em>add</em> is much faster than <em>set</em></strong> (because it does not need to
     * search for existing members). Therefore <em>add</em> should be preferred when constructing new
     * objects.
     * </p>
     *
     * @param name the name of the member to add
     * @param value the value of the member to add, must not be <code>null</code>
     * @return the object itself, to enable method chaining
     */
    public JsonObject add(String name, JsonValue value){
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(value, "value");
        map.put(name, value);
        return this;
    }

    /**
     * Removes a member with the specified name from this object. If this object contains multiple
     * members with the given name, only the last one is removed. If this object does not contain a
     * member with the specified name, the object is not modified.
     *
     * @param name the name of the member to remove
     * @return the object itself, to enable method chaining
     */
    public JsonObject remove(String name){
        Objects.requireNonNull(name, "name");
        map.remove(name);
        return this;
    }

    /**
     * Returns the value of the member with the specified name in this object. If this object contains
     * multiple members with the given name, this method will return the last one.
     *
     * @param name the name of the member whose value is to be returned
     * @return the value of the last member with the specified name, or <code>null</code> if this
     * object does not contain a member with that name
     */
    public JsonValue get(String name){
        Objects.requireNonNull(name, "name");
        return map.get(name);
    }

    /**
     * Returns the <code>int</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one will be picked. If this
     * member's value does not represent a JSON number or if it cannot be interpreted as Java
     * <code>int</code>, an exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public int getInt(String name, int defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asInt() : defaultValue;
    }

    /**
     * Returns the <code>long</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one will be picked. If this
     * member's value does not represent a JSON number or if it cannot be interpreted as Java
     * <code>long</code>, an exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public long getLong(String name, long defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asLong() : defaultValue;
    }

    /**
     * Returns the <code>float</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one will be picked. If this
     * member's value does not represent a JSON number or if it cannot be interpreted as Java
     * <code>float</code>, an exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public float getFloat(String name, float defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asFloat() : defaultValue;
    }

    /**
     * Returns the <code>double</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one will be picked. If this
     * member's value does not represent a JSON number or if it cannot be interpreted as Java
     * <code>double</code>, an exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public double getDouble(String name, double defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asDouble() : defaultValue;
    }

    /**
     * Returns the <code>boolean</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one will be picked. If this
     * member's value does not represent a JSON <code>true</code> or <code>false</code> value, an
     * exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public boolean getBoolean(String name, boolean defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asBoolean() : defaultValue;
    }

    /**
     * Returns the <code>String</code> value of the member with the specified name in this object. If
     * this object does not contain a member with this name, the given default value is returned. If
     * this object contains multiple members with the given name, the last one is picked. If this
     * member's value does not represent a JSON string, an exception is thrown.
     *
     * @param name the name of the member whose value is to be returned
     * @param defaultValue the value to be returned if the requested member is missing
     * @return the value of the last member with the specified name, or the given default value if
     * this object does not contain a member with that name
     */
    public String getString(String name, String defaultValue){
        JsonValue value = get(name);
        return value != null ? value.asString() : defaultValue;
    }

    /**
     * Returns the number of members (name/value pairs) in this object.
     *
     * @return the number of members in this object
     */
    public int size(){
        return map.size();
    }

    /**
     * Returns <code>true</code> if this object contains no members.
     *
     * @return <code>true</code> if this object contains no members
     */
    public boolean isEmpty(){
        return map.isEmpty();
    }

    /**
     * Returns a list of the names in this object in document order. The returned list is backed by
     * this object and will reflect subsequent changes. It cannot be used to modify this object.
     * Attempts to modify the returned list will result in an exception.
     *
     * @return a list of the names in this object
     */
    public Set<String> names(){
        return map.keySet();
    }

    /**
     * Returns an iterator over the members of this object in document order. The returned iterator
     * cannot be used to modify this object.
     *
     * @return an iterator over the members of this object
     */
    public Iterator<Member> iterator(){
        final Iterator<String> namesIterator = names().iterator();
        final Iterator<JsonValue> valuesIterator = map.values().iterator();
        return new Iterator<JsonObject.Member>(){

            public boolean hasNext(){
                return namesIterator.hasNext();
            }

            public Member next(){
                String name = namesIterator.next();
                JsonValue value = valuesIterator.next();
                return new Member(name, value);
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public JsonType getType(){
        return JsonType.OBJECT;
    }

    @Override
    public boolean isObject(){
        return true;
    }

    @Override
    public JsonObject asObject(){
        return this;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        if(!super.equals(o)) return false;
        JsonObject members = (JsonObject)o;
        return map.equals(members.map);
    }

    @Override
    public int hashCode(){
        return Objects.hash(map);
    }

    /**
     * Represents a member of a JSON object, a pair of a name and a value.
     */
    public static class Member{
        private final String name;
        private final JsonValue value;

        Member(String name, JsonValue value){
            this.name = name;
            this.value = value;
        }

        /**
         * Returns the name of this member.
         *
         * @return the name of this member, never <code>null</code>
         */
        public String getName(){
            return name;
        }

        /**
         * Returns the value of this member.
         *
         * @return the value of this member, never <code>null</code>
         */
        public JsonValue getValue(){
            return value;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Member member = (Member)o;
            return name.equals(member.name) && value.equals(member.value);
        }

        @Override
        public int hashCode(){
            return Objects.hash(name, value);
        }
    }
}
