package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expersion;

import java.util.Random;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Rep extends Expersion {

    private Expersion expersion;
    private int minimumLength;
    private int maximumLength;

    public Rep(Expersion expersion, int minimumLength){
        this(expersion,minimumLength,minimumLength);
    }

    public Rep(Expersion expersion, int minimumLength, int maximumLength) {

        if( minimumLength > maximumLength )
            throw new IllegalArgumentException("" + minimumLength + " must be less than or eqaul to" + maximumLength);

        this.expersion = expersion;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    public Expersion getExpersion() {
        return expersion;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    @Override
    public String generate() {

        StringBuilder sb = new StringBuilder();

        int start = 1;
        int length = minimumLength;

        if( this.minimumLength != this.maximumLength ) {
            Random rnd = new Random();
            length = rnd.nextInt( (maximumLength-minimumLength+1) ) + minimumLength;
            start = minimumLength;
        }

        for(int i=start;i<=length;i++){
            sb.append(expersion.generate());
        }

        return sb.toString();
    }
}
