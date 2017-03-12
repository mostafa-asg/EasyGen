package com.github.generator.parser;

import com.github.generator.expersions.Expression;

/**
 * Created by Mostafa on 02/23/2017.
 */
public interface IParser {

    public Expression parse() throws ParseException;

}
