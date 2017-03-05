package com.github.generator.parser;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.sink.ConsoleSink;

/**
 * @author Mostafa Asgari
 * @since 2/28/17
 */
public class ConsoleSinkParser extends AbstractParser {

    public ConsoleSinkParser(Lexer lexer) {
        super(lexer);
    }

    public Expersion parse() throws ParseException {

        ensureNextTokenIs( Token.Type.L_PARENTHESE );
        Expersion exp = parseUntilNextRightParenthese();

        return new ConsoleSink( exp );

    }
}
