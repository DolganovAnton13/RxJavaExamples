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

        //Мы можем настроить Observable так, чтобы метод call
        // был выполнен в другом потоке. Для этого используется оператор subscribeOn,
        // в который нам необходимо передать Scheduler.
        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("TAG","observer onCompleted");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onNext(Integer vaule) {
                Log.i("TAG","observer onNext value = " + vaule);
            }
        };

        Observable.OnSubscribe onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.i("TAG","call");
                for (int i = 0; i < 3; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        };

        Observable<Integer> observable = Observable
                .create(onSubscribe)
                .subscribeOn(Schedulers.io());

        Log.i("TAG","subscribe");
        observable.subscribe(observer);

        Log.i("TAG","done");

    }


}

