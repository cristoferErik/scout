package com.checker.scout.util.paginator;

public class PageItem {
    private int numero;
    private boolean actual;

    public PageItem( int numero,boolean actual) {
        this.actual = actual;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isActual() {
        return actual;
    }

}
