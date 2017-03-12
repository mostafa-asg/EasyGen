package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Newline extends Expression {

    @Override
    public String generate() throws Exception {
        return System.lineSeparator();
    }

}
