package com.sample.rxjavaexamples;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by phanirajabhandari on 4/6/15.
 */
public class AsyncOperators {


    public String sampleFunction(){
        return "Iam Simple Function";
    }

    public static void start() {
        Async.start(new Func0<Object>() {
            @Override
            public Object call() {
                return "Iam Simple Function";
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println(" Its now Observable : " + o);
            }
        });
    }

    public static void toAsync(){
        Async.toAsync(new Action0() {
            @Override
            public void call() {
                System.out.println("CAll ");
            }
        }).call().subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                System.out.println("CAll 2");
            }
        });
    }
}
