package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Самый обычный Subject, без каких-либо опций. Принимает данные и отдает их всем текущим подписчикам.
        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.i("TAG","observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.i("TAG","observer1 onNext value = " + aLong);
            }
        };

        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.i("TAG","observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Long aLong) {
                Log.i("TAG","observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10);

        final PublishSubject<Long> subject = PublishSubject.create();

        Log.i("TAG","subject subscribe");
        observable.subscribe(subject);


        Log.i("TAG","observer1 subscribe");
        subject.subscribe(observer1);

        Log.i("TAG","observer2 subscribe");
        subject.subscribe(observer2);

        subject.onNext(100L);

    }


}

