package com.github.generator.expersions.functions.ranges;

import com.github.generator.expersions.terminals.CharTerminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class CharRange extends Range<CharTerminal> {

    public CharRange(CharTerminal... terminals) {
        super(terminals);
    }
    public CharRange(char start,char end){
        this(new CharTerminal(start),new CharTerminal(end));
    }
    public CharRange(CharTerminal start, CharTerminal end) {
        super(start, end);
    }

    @Override
    protected List<CharTerminal> initRange(CharTerminal startTerminal, CharTerminal endTerminal) {

        int startValue = startTerminal.getValue();
        int endValue = endTerminal.getValue();

        if( startValue > endValue )
            throw new IllegalArgumentException("" + (char)startValue + " must be less than or equal to " + (char)endValue);

        List<CharTerminal> range = new ArrayList<CharTerminal>();
        for( long n = startValue;n<=endValue;n++ ){
            range.add( new CharTerminal( (char)n ) );
        }

        return range;
    }
}
