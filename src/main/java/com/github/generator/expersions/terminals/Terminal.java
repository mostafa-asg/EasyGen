package com.github.generator.expersions.terminals;

import com.github.generator.expersions.Expersion;

/**
 * Created by Mostafa on 02/17/2017.
 */
public abstract class Terminal<T> extends Expersion {

    private T terminal;

    public Terminal(T terminal) {
        this.terminal = terminal;
    }

    public T getValue(){
        return terminal;
    }

    @Override
    public String generate() {
        return terminal.toString();
    }

    @Override
    public String toString() {
        return generate();
    }
}
