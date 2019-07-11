package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Оператор merge объединит элементы из двух Observable в один Observable
        Observable<Integer> observable = Observable
                .from(new Integer[]{1,2,3})
                .mergeWith(Observable.from(new Integer[]{6,7,8,9}));

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
            public void onNext(Integer s) {
                Log.i("tag", "onNext: " + s);
            }
        };

// subscribe
        observable.subscribe(observer);
    }


}

