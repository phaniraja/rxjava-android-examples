package com.sample.rxjavaexamples;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Created by phanirajabhandari on 4/7/15.
 */
public class Connected {
    public static void example1() {
        ConnectableObservable os = Observable.just(1,2,3,4).publish();
        os.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Connectable:: A1  " + integer);
            }
        });
        os.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                System.out.println("Connectable:: A2  " + o);
            }
        });
        os.connect();
    }
}
