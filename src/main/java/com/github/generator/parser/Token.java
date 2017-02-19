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
        DOUBLE_DOT,
        STRING_TERMINAL,
        NUMBER_TERMINAL,
        PLUS,
        MINUS,
        EOF //end of file

    }

    private String name;
    private Token.Type type;

    public Token(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
