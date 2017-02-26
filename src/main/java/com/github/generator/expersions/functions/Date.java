package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

import java.text.SimpleDateFormat;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Date extends Expersion {

    private SimpleDateFormat dateFormat;

    public Date() {
        dateFormat = new SimpleDateFormat();
    }

    public Date(String pattern) {
        dateFormat = new SimpleDateFormat(pattern);
    }

    @Override
    public String generate() throws Exception {
        return dateFormat.format(new java.util.Date());
    }
}
