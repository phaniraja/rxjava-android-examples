package com.sample.rxjavaexamples;

import rx.functions.Action1;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

/**
 * Created by phanirajabhandari on 4/6/15.
 */
public class SubjectExamples {

    public static void Async1(){
        System.out.println("Async Subject Example");
        AsyncSubject<Object> as = AsyncSubject.create();
        as.onNext("Eleven");
        as.onNext("Twelve");
        as.onNext("Thirteen");
        as.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("Async = " + o);
            }
        });
        as.onNext("Fourteen");
        as.onNext("Fifteen");
        as.onNext("Sixteen");// Only this will be emitted
        as.onCompleted();
    }

    public static void beh1(){
        System.out.println("Behaviour Subject Example");
        BehaviorSubject<Object> bs = BehaviorSubject.create();
        bs.onNext("Eleven");
        bs.onNext("Twelve");
        bs.onNext("Thirteen");//From here everything will be emitted
        bs.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("Behaviour = " + o);
            }
        });
        bs.onNext("Fourteen");
        bs.onNext("Fifteen");
        bs.onNext("Sixteen");
        bs.onCompleted();
    }
    
    public static void publish1(){
        System.out.println("Publish Subject Example");
        PublishSubject<Object> ps = PublishSubject.create();
        ps.onNext("Eleven");
        ps.onNext("Twelve");
        ps.onNext("Thirteen");
        ps.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("Publish = " + o);
            }
        });
        //Only Following will be emitted
        ps.onNext("Fourteen");
        ps.onNext("Fifteen");
        ps.onNext("Sixteen");

    }



    public static void replay1(){
        System.out.println("Replay Subject Example");
        ReplaySubject<Object> rs = ReplaySubject.create();
        //Everything will be emitted
        rs.onNext("Eleven");
        rs.onNext("Twelve");
        rs.onNext("thirteen");
        rs.subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                System.out.println("Replay = " + o);
            }
        });

        rs.onNext("Fourteen");
        rs.onNext("Fifteen");
        rs.onNext("Sixteen");
    }
}
