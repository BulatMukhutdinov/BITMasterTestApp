package ru.taximaster.testapp.presentation.module.map;


import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BasePresenter;

public class MapPresenterImpl extends BasePresenter<MapView, MainRouter> implements MapPresenter {

    public MapPresenterImpl(MapView view, MainRouter router) {
        super(view, router);
    }

    @Override
    public void onStart() {
        router.enableToolbar(true);
    }
}