package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observable = Observable.from(new String[]{"one", "two", "three"});

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.i("log","onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("log","onError: " + e);
            }

            @Override
            public void onCompleted() {
                Log.i("log","onCompleted");
            }
        };

        observable.subscribe(observer);
    }
}
