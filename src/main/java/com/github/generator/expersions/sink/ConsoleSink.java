package com.github.generator.expersions.sink;

import com.github.generator.expersions.Expersion;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class ConsoleSink extends Expersion {

    private Expersion expersion;

    public ConsoleSink(Expersion expersion) {
        this.expersion = expersion;
    }

    public Expersion getExpersion() {
        return expersion;
    }

    @Override
    public String generate() throws Exception {

        String output = expersion.generate();

        System.out.print( output );

        return output;
    }

}
