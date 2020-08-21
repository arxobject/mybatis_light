package com.ninjutsu.adapterPattern;

public class Adapter implements IAdapter {

    private Adaptee adaptee = new Adaptee();
    @Override
    public void callerMethod() {
        adaptee.serviceMetho();
    }
}
