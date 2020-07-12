package com.manli.manli_java.util;

public class TupleThree<A, B, C> extends TupleTwo<A, B> {
    public final C third;

    public TupleThree(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }
}
