package com.github.generator.parser;

import com.github.generator.expersions.Expersion;

/**
 * Created by Mostafa on 02/23/2017.
 */
public interface IParser {

    public Expersion parse() throws ParseException;

}
