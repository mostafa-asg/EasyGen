package com.github.generator.expersions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class SequenceExpression extends Expression {

    private List<Expression> expressions = new ArrayList<Expression>();

    public void addExpersion(Expression exp){
        expressions.add( exp );
    }

    public List<Expression> getExpressions(){
        return Collections.unmodifiableList(expressions);
    }

    @Override
    public String generate() throws Exception {

        StringBuilder sb = new StringBuilder();

        for( Expression exp : expressions) {
            sb.append( exp.generate() );
        }

        return sb.toString();
    }
}
