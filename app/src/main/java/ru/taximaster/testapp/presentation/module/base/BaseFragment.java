package ru.taximaster.testapp.presentation.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<P extends Presenter> extends Fragment implements View {
    protected P presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = injectPresenter();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    protected abstract P injectPresenter();
}