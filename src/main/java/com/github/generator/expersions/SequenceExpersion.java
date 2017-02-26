package com.github.generator.expersions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class SequenceExpersion extends Expersion {

    private List<Expersion> expersions = new ArrayList<Expersion>();

    public void addExpersion(Expersion exp){
        expersions.add( exp );
    }

    public List<Expersion> getExpersions(){
        return Collections.unmodifiableList(expersions);
    }

    @Override
    public String generate() throws Exception {

        StringBuilder sb = new StringBuilder();

        for( Expersion exp : expersions ) {
            sb.append( exp.generate() );
        }

        return sb.toString();
    }
}
