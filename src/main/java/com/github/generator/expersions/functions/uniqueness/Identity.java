package com.github.generator.expersions.functions.uniqueness;

import com.github.generator.expersions.Expression;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public class Identity extends Expression {

    private static Expression instance;
    private long increment;
    private long current;

    private Identity(long seed, long increment) {
        current = seed-increment;
        this.increment = increment;
    }

    //This method is just for unit testing and never used in application
    public static void destroy(){
        instance = null;
    }

    public static Expression getInstance(long seed , long increment){
        if( instance == null ){
            instance = new Identity(seed, increment);
        }

        return instance;
    }

    public static Expression getInstance() {
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
