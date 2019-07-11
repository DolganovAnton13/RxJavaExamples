package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

        //Оператор map преобразует все элементы последовательности.
        // Для этого нам необходимо написать функцию преобразования.
        // Например конвертация из String в Integer.
        
        Observable<Integer> observable = Observable
                .from(new String[]{"1", "2", "3", "4", "5", "6"})
                .map(stringToInteger);


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


        observable.subscribe(observer);
    }

    Func1<String, Integer> stringToInteger = new Func1<String, Integer>() {
        @Override
        public Integer call(String s) {
            return Integer.parseInt(s);
        }
    };
}

