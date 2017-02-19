package com.github.generator.expersions;

/**
 * Created by Mostafa on 02/17/2017.
 */
public abstract class Expersion {

    public abstract String generate();

    @Override
    public String toString() {
        return generate();
    }
}
