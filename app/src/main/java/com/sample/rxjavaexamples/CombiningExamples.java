package com.sample.rxjavaexamples;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

/**
 * Created by phanirajabhandari on 4/6/15.
 */
public class CombiningExamples {
    public static void startWith(){
        //Prints: 4123
        Observable.just(1,2,3)
                .startWith(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("integer = " + integer);
                    }
                });
    }

     public static void merge(){
         //Prints 1234
         Observable.merge(Observable.just(1,2), Observable.just(3,4)).subscribe(new Action1<Integer>() {
             @Override
             public void call(Integer integer) {
                 System.out.println("integer = " + integer);
             }
         });
     }

    public static void combineLatest(){
        //Prints 1234
        Observable.combineLatest(Observable.just(1, 2), Observable.just(3, 4), new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                System.out.println("integer = " + integer+"::"+integer2);
                return integer+integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });
    }

    public static void join(){
//        Observable.just(1,2,3,4).join(Observable.just(5),new Func1<Integer, Observable<Object>>() {
//            @Override
//            public Observable<Object> call(Integer integer) {
//                return Observable.just(1);
//            }
//        }, new Func1<Integer, Observable<Object>>() {
//            @Override
//            public Observable<Object> call(Integer integer) {
//              return  Observable.just(2);
//            }
//        }, new Func2<Integer, Object, Object>() {
//            @Override
//            public Object call(Integer integer, Object o) {
//                return  Observable.just(2);
//            }
//        });
    }
}
