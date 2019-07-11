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

        //Оператор buffer собирает элементы и по мере накопления заданного кол-ва отправляет их дальше одним пакетом.
        //
        //Создадим Observable из 8 чисел, и добавим к нему буфер с количеством элементов = 3.
        Observable<List<Integer>> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .buffer(3);

// create observer
        Observer<List<Integer>> observer = new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.i("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG", "onError: " + e);
            }

            @Override
            public void onNext(List<Integer> s) {
                Log.i("TAG", "onNext: " + s);
            }
        };

// subscribe
        observable.subscribe(observer);
    }


}

