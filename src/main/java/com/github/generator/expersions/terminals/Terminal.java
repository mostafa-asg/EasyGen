package com.github.generator.expersions.terminals;

import com.github.generator.expersions.Expression;

/**
 * Created by Mostafa on 02/17/2017.
 */
public abstract class Terminal<T> extends Expression {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal<?> terminal1 = (Terminal<?>) o;

        return this.terminal != null ? terminal.equals(terminal1.terminal) : terminal1.terminal == null;

    }

    @Override
    public int hashCode() {
        return terminal != null ? terminal.hashCode() : 0;
    }
}
