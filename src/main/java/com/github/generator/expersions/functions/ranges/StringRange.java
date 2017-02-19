package com.github.generator.expersions.functions.ranges;

import com.github.generator.expersions.terminals.StringTerminal;

import java.util.List;

/**
 * Created by Mostafa on 02/17/2017.
 */
public class StringRange extends Range<StringTerminal> {

    public StringRange(StringTerminal... terminals) {
        super(terminals);
    }

    @Override
    protected List<StringTerminal> initRange(StringTerminal startTerminal, StringTerminal endTerminal) {
        throw new UnsupportedOperationException("cannot create range from " + startTerminal + " , " + endTerminal);
    }
}
