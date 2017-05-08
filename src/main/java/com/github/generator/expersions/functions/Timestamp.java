package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mostafa Asgari
 * @since 3/14/17
 */
public class Timestamp extends Expression {

    private ThreadLocalRandom rnd = ThreadLocalRandom.current();
    private Long from;
    private Long to;

    public Timestamp() {
    }

    public Timestamp(Long from) {
        this.from = from;
    }

    public Timestamp(Long from, Long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String generate() throws Exception {

        long current = new Date().getTime();

        if( from == null && to == null )
            return String.valueOf( current );

        if( from != null && to == null ){
            return String.valueOf(rnd.nextLong( from , current+1 ));
        }
        else{
            return String.valueOf(rnd.nextLong( from , to+1 ));
        }
    }
}
