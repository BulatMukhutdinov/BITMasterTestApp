package ru.taximaster.testapp.presentation.module.detailed;


import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BasePresenter;

public class DetailedPresenterImpl extends BasePresenter<DetailedView, MainRouter> implements DetailedPresenter {

    public DetailedPresenterImpl(DetailedView view, MainRouter router) {
        super(view, router);
    }

    @Override
    public void onStart() {
        router.enableToolbar(true);
    }
}