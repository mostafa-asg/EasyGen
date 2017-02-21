package com.github;

import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.SequenceExpersion;
import com.github.generator.expersions.functions.ranges.CharRange;
import com.github.generator.expersions.functions.ranges.LongRange;
import com.github.generator.expersions.terminals.CharTerminal;
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

        Assert.assertEquals( 2 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof StringTerminal);
        Assert.assertEquals( "Hello" , ((StringTerminal)expList.get(0)).getValue() );

        Assert.assertTrue( expList.get(1) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(1));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(1);
        while (it.hasNext()){
             Assert.assertEquals(num,it.next().getValue());
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

        Assert.assertEquals( 2 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(120);
        while (it.hasNext()){
            Assert.assertEquals(num,it.next().getValue());
            ++num;
        }

        Assert.assertTrue( expList.get(1) instanceof StringTerminal);
        Assert.assertEquals( "Hello" , ((StringTerminal)expList.get(1)).getValue() );

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

        Assert.assertEquals( 3 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();
        Long num = new Long(100);
        while (it.hasNext()){
            Assert.assertEquals(num,it.next().getValue());
            ++num;
        }

        Assert.assertTrue( expList.get(1) instanceof StringTerminal);
        Assert.assertEquals( "Hello" , ((StringTerminal)expList.get(1)).getValue() );

        Assert.assertTrue( expList.get(2) instanceof LongRange);
        longRange = ((LongRange)expList.get(2));
        it = longRange.iterator();
        num = new Long(5);
        while (it.hasNext()){
            Assert.assertEquals(num,it.next().getValue());
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

        Assert.assertEquals( 1 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(0));
        Iterator<LongTerminal> it = longRange.iterator();

        Long num = new Long(0);
        while (it.hasNext()){
            Assert.assertEquals(num,it.next().getValue());
            ++num;
        }
    }

    @Test
    public void test5() throws ParseException {

        String input = "[a..z]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( 1 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange)expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();

        char ch = 'a';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'z' );
    }

    @Test
    public void test6() throws ParseException {

        String input = "[a..c][d..g]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( 2 , expList.size() );

        Assert.assertTrue( expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange)expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'a';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'c' );

        Assert.assertTrue( expList.get(1) instanceof CharRange);
        charRange = ((CharRange)expList.get(1));
        it = charRange.iterator();
        ch = 'd';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'g' );
    }

    @Test
    public void test7() throws ParseException {

        String input = "[a..c][1..5][d..g][666..777]";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( 4 , expList.size() );

        //-----------------------------------------------------
        Assert.assertTrue( expList.get(0) instanceof CharRange);
        CharRange charRange = ((CharRange)expList.get(0));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'a';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'c' );
        //-----------------------------------------------------
        Assert.assertTrue( expList.get(1) instanceof LongRange);
        LongRange longRange = ((LongRange)expList.get(1));
        Iterator<LongTerminal> itLong = longRange.iterator();

        Long num = new Long(1);
        while (itLong.hasNext()){
            Assert.assertEquals(num,itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals( new Long(5) , --num );
        //-----------------------------------------------------
        Assert.assertTrue( expList.get(2) instanceof CharRange);
        charRange = ((CharRange)expList.get(2));
        it = charRange.iterator();
        ch = 'd';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'g' );
        //-----------------------------------------------------
        Assert.assertTrue( expList.get(3) instanceof LongRange);
        longRange = ((LongRange)expList.get(3));
        itLong = longRange.iterator();

        num = new Long(666);
        while (itLong.hasNext()){
            Assert.assertEquals(num,itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals( new Long(777) , --num );

    }

    @Test
    public void test8() throws ParseException {
        String input = "This is a sentence[A..Z].And this is another[1..7] sentence";
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);

        SequenceExpersion seqExp = parser.parse();
        List<Expersion> expList = seqExp.getExpersions();

        Assert.assertEquals( 11 , expList.size() );
        Assert.assertTrue( seqExp.getExpersions().get(4) instanceof CharRange );
        Assert.assertTrue( seqExp.getExpersions().get(9) instanceof LongRange );

        //------------------------------------------------
        CharRange charRange = ((CharRange)expList.get(4));
        Iterator<CharTerminal> it = charRange.iterator();
        char ch = 'A';
        while (it.hasNext()){
            Assert.assertTrue( it.next().getValue()==ch );
            ++ch;
        }
        Assert.assertTrue( --ch == 'Z' );
        //------------------------------------------------
        LongRange longRange = ((LongRange)expList.get(9));
        Iterator<LongTerminal> itLong = longRange.iterator();

        Long num = new Long(1);
        while (itLong.hasNext()){
            Assert.assertEquals(num,itLong.next().getValue());
            ++num;
        }
        Assert.assertEquals( new Long(7) , --num );
    }
}
