package com.sample.rxjavaexamples;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import rx.schedulers.Timestamped;

/**
 * Created by phanirajabhandari on 4/3/15.
 */
public class RxExamples {
    public static void flatmap1() {
        String[] cities = new String[2];
        cities[0] = "Hyderabad";
        cities[1] = "Austin";
        Observable.from(cities)
                .flatMap(new Func1<String, Observable<WeatherData>>() {
                    @Override
                    public Observable<WeatherData> call(String s) {
                        return ApiManager.getWeatherData(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherData>() {
                    @Override
                    public void call(WeatherData weatherData) {
                        Log.d(MainActivity.LOG_TAG, " weather :" + weatherData.sys.country);
                    }
                });
    }

    public static void just() {
        Observable.just("Hello, world!")
                .subscribe(s -> {
                    System.out.println(s);
                });
    }

    public static void create() {
        Observable<String> myObservable = Observable.create(
                sub -> {
                    sub.onNext("Hello, world !");
                    sub.onCompleted();
                }
        );
        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };
        myObservable.subscribe(mySubscriber);
    }

    public static void flatMap2() {
        query().flatMap(strings -> Observable.from(strings))
                .subscribe(url -> System.out.println(url));
    }

    static Observable<List<String>> query() {
        List<String> ss = new ArrayList<String>();
        ss.add("1111111");
        ss.add("2222222");
        ss.add("3333333");
        return Observable.just(ss);
    }


    public static void doOnNext() {
        Observable
                .just(1, 2, 3)
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        if (integer.equals(2)) {
                            throw new RuntimeException("I don't like 2");
                        }
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed Observable.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.err.println("Whoops: " + throwable.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }


    public static void take() {
        Observable
                .just("Hyderabad", "Austin", "London", "Paris", "sweden", "sydney")
                .take(2)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted.");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.err.println("onError: " + throwable.getMessage());
                    }

                    @Override
                    public void onNext(String name) {
                        System.out.println("Got: " + name);
                    }
                });
    }

    public static void subscriberWithAction() {
        Observable
                .just(1, 2, 3)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println("Got: " + integer);
                    }
                });
    }

    public static void parallelThreadsCommunication() {
        final CountDownLatch latch = new CountDownLatch(5);
        Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long counter) {
                        latch.countDown();
                        System.out.println("Got: " + counter);
                    }
                });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void toBlocking() {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .take(5)
                .toBlocking()
                .forEach(new Action1<Long>() {
                    @Override
                    public void call(Long counter) {
                        System.out.println("Got: " + counter);
                    }
                });
    }

    public static void single() {
        int value = Observable
                .just(1)
                .toBlocking()
                .single();
        System.out.println("Single : " + value);
    }

    public static void singleOrDefault() {
        String value = (String) Observable
                .create(subscriber -> subscriber.onCompleted())
                .toBlocking()
                .singleOrDefault(null);
        // Prints: null
        System.out.println("SingleOrDefault ::: " + value);
    }


    public static void toList() {
        List<Integer> list = Observable
                .just(1, 2, 3)
                .toList()
                .toBlocking()
                .single();

        // Prints: [1, 2, 3]
        System.out.println(list);
    }

    public static void create1() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Got: " + integer);
            }
        });
    }

    public static void map() {
        Observable.just(1, 2, 3, 4)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        switch (integer) {
                            case 1:
                                return "ONE";
                            case 2:
                                return "TWO";
                            case 3:
                                return "Three";

                        }
                        return "FOUR";
                    }
                }).take(3).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                System.out.println(" :" + o);
            }
        });
    }

    public static void scan() {
        Observable
                .just(1, 2, 3, 4, 5)
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer sum, Integer value) {
                        return sum + value;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("Sum: " + integer);
            }
        });
    }

    //Integers  : [1, 3, 5]
    //Integers  : [2, 4]
    public static void groupBy() {
        Observable
                .just(1, 2, 3, 4, 5)
                .groupBy(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                }).subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void call(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) {
                booleanIntegerGroupedObservable.toList().subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        System.out.println(" Integers  : " + integers);
                    }
                });
            }
        });
    }


    //filter :3
    //filter :4
    public static void filter() {
        Observable
                .just(1, 2, 3, 4)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("filter :" + integer);
            }
        });
    }

    public static void distinct() {
        // 1, 2, 3, 4 will be emitted
        Observable
                .just(1, 2, 1, 3, 4, 2)
                .distinct()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(" Result :" + integer);
                    }
                });
    }

    public static void merge() {
        Integer[] evens = new Integer[]{2, 4, 6, 8};
        Integer[] odds = new Integer[]{1, 3, 5, 7};
        Observable
                .merge(Observable.just(evens), Observable.just(odds))
                .subscribe(new Action1<Integer[]>() {
                    @Override
                    public void call(Integer[] integers) {
                        System.out.println(" merge :" + Arrays.asList(integers));
                    }
                });

    }

    public static void zip() {
        Integer[] evens = new Integer[]{2, 4, 6, 8, 10};
        Integer[] odds = new Integer[]{1, 3, 5, 7};
        Observable.zip(Observable.just(evens), Observable.just(odds), new Func2<Integer[], Integer[], Integer>() {
            @Override
            public Integer call(Integer[] evens, Integer[] odds) {
                return evens.length + odds.length;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer size) {
                System.out.println(" ZIP size:" + size);
            }
        });
    }

    public static void ErrorHandling1() {
        Observable.just("Mango", "Banana", "Apples")
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        throw new RuntimeException("I dont like Banana!");
                    }
                })
                .onErrorReturn(new Func1<Throwable, String>() {
                    @Override
                    public String call(Throwable throwable) {
                        System.out.println("Something went Wrong! :" + throwable);
                        return "Orange";
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(" EH :" + s);
            }
        });
    }

    public static void materlize(){

        Observable.range(2, 4).timestamp().subscribe(new Action1<Timestamped<Integer>>() {
            @Override
            public void call(Timestamped<Integer> integerTimestamped) {
                System.out.println("MATER!! ::" + integerTimestamped.getTimestampMillis());
            }
        });

        Observable.range(2, 4).materialize().subscribe(new Action1<Notification<Integer>>() {
            @Override
            public void call(Notification<Integer> integerNotification) {
                System.out.println("MATER@@ ::"+integerNotification.hasValue()+"::"+integerNotification.hasThrowable());
            }
        });
    }
}

