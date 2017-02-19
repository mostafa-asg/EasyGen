package com.github.generator.expersions.functions.ranges;

import com.github.generator.expersions.terminals.LongTerminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class LongRange extends Range<LongTerminal> {

    public LongRange(LongTerminal... terminals) {
        super(terminals);
    }
    public LongRange(long start,long end){
        this(new LongTerminal(start),new LongTerminal(end));
    }
    public LongRange(LongTerminal start, LongTerminal end) {
        super(start, end);
    }

    @Override
    protected List<LongTerminal> initRange(LongTerminal startTerminal, LongTerminal endTerminal) {

        long startValue = startTerminal.getValue();
        long endValue = endTerminal.getValue();

        if( startValue > endValue )
            throw new IllegalArgumentException("" + startValue + " must be less than or equal to " + endValue);

        List<LongTerminal> range = new ArrayList<LongTerminal>();
        for( long n = startValue;n<=endValue;n++ ){
            range.add( new LongTerminal(n) );
        }

        return range;
    }
}
