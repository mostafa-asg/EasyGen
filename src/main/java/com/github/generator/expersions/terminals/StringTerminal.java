package com.github.generator.expersions.terminals;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class StringTerminal extends Terminal<String> {

    public StringTerminal(String terminal) {
        super(terminal);
    }

    public static StringTerminal valueOf(String value){
        return new StringTerminal(value);
    }
}
