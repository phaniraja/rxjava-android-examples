package com.sample.rxjavaexamples;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    public static String LOG_TAG = "RX_JAVA_Examples";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        RxExamples.doOnNext();
//        RxExamples.take();
//        RxExamples.subscriberWithAction();
//        RxExamples.parallelThreadsCommunication();
//        RxExamples.toBlocking();

//        RxExamples.single();
//        RxExamples.toList();
//        RxExamples.create1();
//        RxExamples.map();
//        RxExamples.scan();

//        RxExamples.groupBy();

//        RxExamples.filter();
//        RxExamples.distinct();
//        RxExamples.merge();
//        RxExamples.zip();
//        RxExamples.ErrorHandling1();

//        SubjectExamples.Async1();
//        SubjectExamples.beh1();
//        SubjectExamples.publish1();
//        SubjectExamples.replay1();


//        AsyncOperators.start();
//        AsyncOperators.toAsync();
//        BlockingObser.blockLast();

//        CombiningExamples.startWith();
//          CombiningExamples.merge();
//        CombiningExamples.combineLatest();
//        Connected.example1();
        RxExamples.materlize();
    }
}