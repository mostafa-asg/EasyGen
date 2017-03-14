package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

import java.text.SimpleDateFormat;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Today extends Expression {

    private SimpleDateFormat dateFormat;

    public Today() {
        dateFormat = new SimpleDateFormat();
    }

    public Today(String pattern) {
        dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String generate() throws Exception {
        return dateFormat.format(new java.util.Date());
    }
}
