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

        //Этот оператор аналогичен оператору onErrorReturn,
        // но позволяет вместо ошибки отправить в Observer не одно значение, а несколько - в виде Observable.
        Observable<String> stringData = Observable.just("1", "2", "a", "4", "5");

        Observable<Long> observable = stringData
                .map(new Func1<String, Long>() {
                    @Override
                    public Long call(String s) {
                        return Long.parseLong(s);
                    }
                })
                .onErrorResumeNext(Observable.just(8L, 9L, 10L));

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.i("TAG","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","onError " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.i("TAG","onNext " + aLong);
            }
        });

    }


}

