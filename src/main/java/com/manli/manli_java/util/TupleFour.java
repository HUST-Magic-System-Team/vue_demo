package com.manli.manli_java.util;

public class TupleFour<A, B, C, D> extends TupleThree<A, B, C> {
    public final D fourth;

    public TupleFour(A first, B second, C third, D fourth) {
        super(first, second, third);
        this.fourth = fourth;
    }
}
