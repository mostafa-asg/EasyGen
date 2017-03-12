package com.github.generator.expersions.functions;

import com.github.generator.expersions.Expression;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class PadLeft extends Expression {

    private Expression expression;
    private int minimumLength;
    private char fillWith;

    public PadLeft(Expression expression, int minimumLength, char fillWith) {
        this.expression = expression;
        this.minimumLength = minimumLength;
        this.fillWith = fillWith;
    }

    public Expression getExpression() {
        return expression;
    }

    public int getMinimumLength() {
        return minimumLength;
    }

    public char getFillWith() {
        return fillWith;
    }

    @Override
    public String generate() throws Exception {

        String value = expression.generate();

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
