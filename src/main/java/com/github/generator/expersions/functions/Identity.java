package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public class Identity extends Expersion {

    private static Expersion instance;
    private long increment;
    private long current;

    private Identity(long seed, long increment) {
        current = seed-increment;
        this.increment = increment;
    }

    public static Expersion getInstance(long seed , long increment){
        if( instance == null ){
            instance = new Identity(seed, increment);
        }

        return instance;
    }

    public static Expersion getInstance() {
        if(instance == null)
            throw new RuntimeException("Identity must be initialized");
        return instance;
    }

    @Override
    public String generate() throws Exception {
        current += increment;
        return String.valueOf( current );
    }
}
