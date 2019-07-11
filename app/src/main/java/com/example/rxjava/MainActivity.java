package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Давайте создадим свой Observable.
        //Для этого необходимо написать реализацию интерфейса OnSubscribe и передать ее в метод Observable.create.
        //Создадим Observable, который будет похож на оператор interval.
        // Он будет посылать числа от 0 до 9 с интервалом в одну секунду.

        // create onSubscribe
        Observable.OnSubscribe<Integer> onSubscribe = new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        };

// create observable
        Observable<Integer> observable = Observable.create(onSubscribe)
                .subscribeOn(Schedulers.io());

// create observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("tag", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag", "onError: " + e);
            }

            @Override
            public void onNext(Integer i) {
                Log.i("tag", "onNext: " + i);
            }
        };

// subscribe
        observable.subscribe(observer);
    }


}

