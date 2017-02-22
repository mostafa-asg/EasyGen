package com.github.generator.expersions.functions.ranges;


import com.github.generator.expersions.Expersion;
import com.github.generator.expersions.terminals.Terminal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Mostafa on 02/17/2017.
 */
public abstract class Range<T extends Terminal> extends Expersion implements Iterable<T> {

    private Random rnd = new Random();

    protected List<T> range;

    public Range(){
        range = new ArrayList<T>();
    }

    public Range(T... terminals ){
        this.range = initRange(terminals);
    }

    public Range(T start , T end){
        this.range = initRange(start,end);
    }

    protected abstract List<T> initRange(T startTerminal,T endTerminal);

    protected List<T> initRange(T... terminals){
        List<T> result = new ArrayList<T>(terminals.length);
        for( T terminal : terminals )
            result.add(terminal);
        return result;
    }

    public T getRandom() {
        return range.get( rnd.nextInt( range.size() ) );
    }

    public Range<T> add(Range<T> otherRange){
        Iterator<T> it = otherRange.iterator();
        while (it.hasNext()){
            this.range.add( it.next() );
        }

        return this;
    }

    public Range<T> addExclude( Range<T>  otherRange ){

        Iterator<T> it = otherRange.iterator();
        while (it.hasNext()){
            addExclude( it.next() );
        }

        return this;
    }

    public Range<T> addExclude( T terminal ){
        range.remove( terminal );
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return range.iterator();
    }

    @Override
    public String generate() {
        return getRandom().generate();
    }
}
