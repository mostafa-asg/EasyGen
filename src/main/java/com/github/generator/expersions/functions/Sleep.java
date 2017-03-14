package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

/**
 * @author Mostafa Asgari
 * @since 3/14/17
 */
public class Sleep extends Expression {

    private long millis;

    public Sleep(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return millis;
    }

    @Override
    public String generate() throws Exception {

        Thread.currentThread().sleep(getMillis());

        return "";
    }
}
