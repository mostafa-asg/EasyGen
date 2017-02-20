package com.github;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.terminals.LongTerminal;
import com.github.generator.expersions.terminals.StringTerminal;
import com.github.generator.parser.Lexer;
import com.github.generator.parser.ParseException;
import com.github.generator.parser.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * @author Mostafa Asgari
 * @since 2/20/17
 */

public class TestCase {

    @Test
    public void test1() throws ParseException {

        String input = "Hello[1..100]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( expList.size() , 2 );

        Assert.assertTrue( expList.get(0) instanceof StringTerminal);
        Assert.assertEquals( ((StringTerminal)expList.get(0)).getValue() , "Hello" );

        Assert.assertTrue( expList.get(1) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(1));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(1);
        while (it.hasNext()){
             Assert.assertEquals(it.next().getValue(),num);
            ++num;
        }

        String generatedStr = seqExp.generate();
        Assert.assertTrue( generatedStr.startsWith("Hello") );
        long randomNum = Long.valueOf(generatedStr.substring( "Hello".length() ));
        Assert.assertTrue( randomNum >= 1 && randomNum <= 100 );
    }

    @Test
    public void test2() throws ParseException {

        String input = "[120..140]Hello";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( expList.size() , 2 );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(120);
        while (it.hasNext()){
            Assert.assertEquals(it.next().getValue(),num);
            ++num;
        }

        Assert.assertTrue( expList.get(1) instanceof StringTerminal);
        Assert.assertEquals( ((StringTerminal)expList.get(1)).getValue() , "Hello" );

        String generatedStr = seqExp.generate();
        Assert.assertTrue( generatedStr.length() == 8 );
        Assert.assertTrue( generatedStr.endsWith("Hello") );
        long randomNum = Long.valueOf(generatedStr.substring( 0 , 3 ));
        Assert.assertTrue( randomNum >= 120 && randomNum <= 140 );
    }

    @Test
    public void test3() throws ParseException {

        String input = "[100..119]Hello[5..9]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( expList.size() , 3 );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(100);
        while (it.hasNext()){
            Assert.assertEquals(it.next().getValue(),num);
            ++num;
        }

        Assert.assertTrue( expList.get(1) instanceof StringTerminal);
        Assert.assertEquals( ((StringTerminal)expList.get(1)).getValue() , "Hello" );

        Assert.assertTrue( expList.get(2) instanceof LongRange);
        longRange = ((LongRange)expList.get(2));
        it = longRange.iterator();
        num = new Long(5);
        while (it.hasNext()){
            Assert.assertEquals(it.next().getValue(),num);
            ++num;
        }

        String generatedStr = seqExp.generate();
        Assert.assertTrue( generatedStr.length() == 9 );
        Assert.assertTrue( generatedStr.contains("Hello") );
        long randomNum = Long.valueOf(generatedStr.substring( 0 , 3 ));
        Assert.assertTrue( randomNum >= 100 && randomNum <= 119 );
        randomNum = Long.valueOf(generatedStr.substring( generatedStr.length()-1 , generatedStr.length() ));
        Assert.assertTrue( randomNum >= 5 && randomNum <= 9 );
    }

    @Test
    public void test4() throws ParseException {

        String input = "[0..43]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( expList.size() , 1 );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(0);
        while (it.hasNext()){
            Assert.assertEquals(it.next().getValue(),num);
            ++num;
        }
    }

}
