package com.github.generator.expersions.terminals;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class LongTerminal extends Terminal<Long> {
    public LongTerminal(Long terminal) {
        super(terminal);
    }

    public static LongTerminal valueOf(long value){
        return new LongTerminal(value);
    }
}
