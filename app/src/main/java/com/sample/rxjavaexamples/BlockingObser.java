package com.sample.rxjavaexamples;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.observables.BlockingObservable;

/**
 * Created by phanirajabhandari on 4/6/15.
 */
public class BlockingObser {

    public static void normalLast(){
        Observable.just(1, 2, 3, 4)
                .last().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    public static void blockLast(){
        int lastValue = BlockingObservable.from(Observable.just(1,2,3,4)).last();

        int llv = BlockingObservable.from(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                System.out.println("call");
                subscriber.onCompleted();
            }
        })).firstOrDefault(10);
        System.out.println(""+llv);

    }
}
