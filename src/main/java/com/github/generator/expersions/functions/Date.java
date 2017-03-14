package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mostafa Asgari
 * @since 3/14/17
 */
public class Date extends Expression {

    private SimpleDateFormat dateFormat;
    private java.util.Date start;
    private java.util.Date end;


    public Date(java.util.Date start, java.util.Date end) {
        this(start, end , "");
    }

    public java.util.Date getStartDate() {
        return start;
    }

    public java.util.Date getEndDate() {
        return end;
    }

    public Date(java.util.Date start, java.util.Date end, String pattern) {

        long startTS = start.getTime();
        long endTS = end.getTime();

        if( startTS > endTS )
            throw new IllegalArgumentException("first date must be less than or equal to the second date");

        this.start = start;
        this.end = end;

        if( pattern==null || pattern.equals("") )
            dateFormat = new SimpleDateFormat();
        else
            dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String generate() throws Exception {

        long randomDate = ThreadLocalRandom.current().nextLong(start.getTime(),end.getTime()+1);

        return dateFormat.format(new java.util.Date(randomDate));

    }
}
