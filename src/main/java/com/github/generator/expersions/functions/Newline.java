package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Newline extends Expersion {

    @Override
    public String generate() {
        return System.lineSeparator();
    }

}
