package com.manli.manli_java.util;

public class TupleTwo<A, B> extends TupleOne<A> {
    public final B second;

    public TupleTwo(A first, B second) {
        super(first);
        this.second = second;
    }
}
