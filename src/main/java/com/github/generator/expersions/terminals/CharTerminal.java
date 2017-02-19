package com.github.generator.expersions.terminals;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class CharTerminal extends Terminal<Character> {
    public CharTerminal(Character terminal) {
        super(terminal);
    }

    public static CharTerminal valueOf(char value){
        return new CharTerminal(value);
    }

}
