package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class PadLeft extends Expersion {

    private Expersion expersion;
    private int minimumLength;
    private char fillWith;

    public PadLeft(Expersion expersion, int minimumLength, char fillWith) {
        this.expersion = expersion;
        this.minimumLength = minimumLength;
        this.fillWith = fillWith;
    }

    @Override
    public String generate() {

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

            return sb.toString() + value;
        }

    }
}
