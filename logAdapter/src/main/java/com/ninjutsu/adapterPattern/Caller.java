package com.ninjutsu.adapterPattern;

/**
 * 服务调用方
 */
public class Caller {
    public void call(){
        IAdapter adapter = new Adapter();
        adapter.callerMethod();
    }
}
