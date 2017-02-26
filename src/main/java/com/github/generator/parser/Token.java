package com.github.generator.parser;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Token {

    public enum Type {

        SINGLE_QUOTES ,
        COMMA ,
        L_PARENTHESE ,
        R_PARENTHESE ,
        L_BRACKET,
        R_BRACKET,
        PIPE ,
        DOUBLE_DOT,
        STRING,
        CHAR ,
        NUMBER ,
        FUNCTION,
        PLUS,
        MINUS,
        EOF //end of file

    }

    private String value;
    private Token.Type type;

    public Token(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public Long getValueAsLong(){
        return Long.valueOf(value);
    }
    public Integer getValueAsInt(){
        return Integer.valueOf(value);
    }

    public char getValueAsChar(){
        if( value.length() != 1 ){
            throw new Error("cannot cast " + value + " as a char");
        }
        return value.charAt(0);
    }

    public boolean getValueAsBoolean(){
        return Boolean.parseBoolean(value);
    }

    public Type getType() {
        return type;
    }
}
