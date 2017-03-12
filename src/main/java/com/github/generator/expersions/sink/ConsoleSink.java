package com.github.generator.expersions.sink;

import com.github.generator.expersions.Expression;

/**
 * @author Mostafa Asgari
 * @since 2/26/17
 */
public class ConsoleSink extends Expression {

    private Expression expression;

    public ConsoleSink(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String generate() throws Exception {

        String output = expression.generate();

        System.out.print( output );

        return output;
    }

}
