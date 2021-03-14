package org.hjson;

public class JsonBoolean extends JsonValue{
    public static final JsonValue TRUE = new JsonBoolean(true);

    public static final JsonValue FALSE = new JsonBoolean(false);

    private final boolean value;

    private JsonBoolean(boolean value){
        this.value = value;
    }

    @Override
    public JsonType getType(){
        return JsonType.BOOLEAN;
    }

    @Override
    public boolean isTrue(){
        return value;
    }

    @Override
    public boolean isFalse(){
        return !value;
    }

    @Override
    public boolean asBoolean(){
        return value;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        JsonBoolean that = (JsonBoolean)o;
        return value == that.value;
    }

    @Override
    public int hashCode(){
        return value ? 1231 : 1237;
    }

    @Override
    public String toString(){
        return Boolean.toString(value);
    }
}
