package ru.taximaster.testapp.presentation.util;


import android.support.annotation.NonNull;

import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.detailed.DetailedPresenter;
import ru.taximaster.testapp.presentation.module.detailed.DetailedPresenterImpl;
import ru.taximaster.testapp.presentation.module.detailed.DetailedView;
import ru.taximaster.testapp.presentation.module.map.MapPresenter;
import ru.taximaster.testapp.presentation.module.map.MapPresenterImpl;
import ru.taximaster.testapp.presentation.module.map.MapView;
import ru.taximaster.testapp.presentation.module.preview.PreviewPresenter;
import ru.taximaster.testapp.presentation.module.preview.PreviewPresenterImpl;
import ru.taximaster.testapp.presentation.module.preview.PreviewView;
import ru.taximaster.testapp.presentation.module.preview.photos.PhotosPresenter;
import ru.taximaster.testapp.presentation.module.preview.photos.PhotosPresenterImpl;
import ru.taximaster.testapp.presentation.module.preview.photos.PhotosView;

public class PresenterInjector {

    private PresenterInjector() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @NonNull
    public static PhotosPresenter providePreviewPresenter(PhotosView photosView, MainRouter router) {
        return new PhotosPresenterImpl(photosView, router);
    }

    @NonNull
    public static DetailedPresenter provideDetailedPresenter(DetailedView detailedView, MainRouter router) {
        return new DetailedPresenterImpl(detailedView, router);
    }

    @NonNull
    public static PreviewPresenter providePreviewPresenter(PreviewView previewView, MainRouter router) {
        return new PreviewPresenterImpl(previewView, router);
    }

    @NonNull
    public static MapPresenter provideMapPresenter(MapView mapView, MainRouter router) {
        return new MapPresenterImpl(mapView, router);
    }
}