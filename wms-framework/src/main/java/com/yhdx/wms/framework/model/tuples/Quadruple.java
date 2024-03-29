package com.yhdx.wms.framework.model.tuples;


public class Quadruple<T1, T2, T3, T4> extends Triple<T1, T2, T3> {

    private T4 item4;

    public Quadruple() {}

    public Quadruple(T1 item1, T2 item2, T3 item3, T4 item4) {
        super(item1, item2, item3);
        this.item4 = item4;
    }

    public final T4 getItem4() {
        return item4;
    }

    public void setItem4(T4 item4) {
        this.item4 = item4;
    }
}
