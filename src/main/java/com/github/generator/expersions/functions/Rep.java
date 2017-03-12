package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

import java.util.Random;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class Rep extends Expression {

    private Expression expression;
    private int minimumLength;
    private int maximumLength;

    public Rep(Expression expression, int minimumLength){
        this(expression,minimumLength,minimumLength);
    }

    public Rep(Expression expression, int minimumLength, int maximumLength) {

        if( minimumLength > maximumLength )
            throw new IllegalArgumentException("" + minimumLength + " must be less than or eqaul to" + maximumLength);

        this.expression = expression;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
    }

    public Expression getExpression() {
        return expression;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    @Override
    public String generate() throws Exception {

        StringBuilder sb = new StringBuilder();

        int start = 1;
        int length = minimumLength;

        if( this.minimumLength != this.maximumLength ) {
            Random rnd = new Random();
            length = rnd.nextInt( (maximumLength-minimumLength+1) ) + minimumLength;
        }

        for(int i=start;i<=length;i++){
            sb.append(expression.generate());
        }

        return sb.toString();
    }
}
