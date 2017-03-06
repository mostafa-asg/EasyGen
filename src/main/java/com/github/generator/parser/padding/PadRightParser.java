package com.github.generator.parser.padding;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.functions.PadRight;
import com.github.generator.parser.Lexer;

/**
 * @author Mostafa Asgari
 * @since 3/6/17
 */
public class PadRightParser extends PadParser {

    public PadRightParser(Lexer lexer) {
        super(lexer);
    }

    @Override
    public Expersion getPad(Expersion firstParam, int minLength, char fillWith) {
        return new PadRight( firstParam , minLength , fillWith );
    }
}
