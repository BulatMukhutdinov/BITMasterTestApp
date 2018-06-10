package ru.taximaster.testapp.presentation.util;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public class SearchBus {
    private PublishSubject<String> bus = PublishSubject.create();

    public Observable<String> toObservable() {
        return bus;
    }

    public void post(String text) {
        if (bus.hasObservers()) {
            bus.onNext(text);
        }
    }
}