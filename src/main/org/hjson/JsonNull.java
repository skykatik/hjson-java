package org.hjson;

public class JsonNull extends JsonValue{
    public static final JsonValue instance = new JsonNull();

    private JsonNull(){}

    @Override
    public JsonType getType(){
        return JsonType.NULL;
    }

    @Override
    public boolean isNull(){
        return true;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof JsonNull;
    }

    @Override
    public String toString(){
        return "null";
    }

    private Object readResolve(){
        return instance;
    }
}
