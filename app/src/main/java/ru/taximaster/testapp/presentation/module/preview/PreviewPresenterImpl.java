package ru.taximaster.testapp.presentation.module.preview;


import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BasePresenter;

public class PreviewPresenterImpl extends BasePresenter<PreviewView, MainRouter> implements PreviewPresenter {

    public PreviewPresenterImpl(PreviewView view, MainRouter router) {
        super(view, router);
    }
}