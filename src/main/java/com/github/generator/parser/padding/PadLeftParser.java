package com.github.generator.parser.padding;

import com.github.generator.expersions.Expression;
import com.github.generator.expersions.functions.PadLeft;
import com.github.generator.parser.Lexer;

/**
 * @author Mostafa Asgari
 * @since 3/5/17
 */
public class PadLeftParser extends PadParser {

    public PadLeftParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expression getPad(Expression firstParam, int minLength, char fillWith) {
        return new PadLeft( firstParam , minLength , fillWith );
    }
}
