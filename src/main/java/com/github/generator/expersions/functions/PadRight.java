package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class PadRight extends Expersion {

    private Expersion expersion;
    private int minimumLength;
    private char fillWith;

    public PadRight(Expersion expersion, int minimumLength, char fillWith) {
        this.expersion = expersion;
        this.minimumLength = minimumLength;
        this.fillWith = fillWith;
    }

    public Expersion getExpersion() {
        return expersion;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public char getFillWith() {
        return fillWith;
    }

    @Override
    public String generate() throws Exception {

        String value = expersion.generate();

        if( value.length() >= minimumLength ) {
            return value;
        }
        else {

            int pading = minimumLength - value.length();
            StringBuilder sb = new StringBuilder(pading);
            for( int i=0;i<pading;i++ ){
                sb.append( fillWith );
            }

            return value + sb.toString();
        }

    }

}
