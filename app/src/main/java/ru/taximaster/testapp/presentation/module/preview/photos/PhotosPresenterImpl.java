package ru.taximaster.testapp.presentation.module.preview.photos;


import android.arch.lifecycle.ViewModelProviders;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.taximaster.testapp.App;
import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.dto.PhotoDto;
import ru.taximaster.testapp.data.dto.ResponseDto;
import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BasePresenter;
import ru.taximaster.testapp.presentation.module.preview.PreviewViewModel;

public class PhotosPresenterImpl extends BasePresenter<PhotosView, MainRouter> implements PhotosPresenter {

    private static final String OK_STATUS = "ok";

    private CompositeDisposable compositeDisposable;

    public PhotosPresenterImpl(PhotosView view, MainRouter router) {
        super(view, router);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onStart(int page) {
        PreviewViewModel previewViewModel = ViewModelProviders.of((PhotosFragment) view).get(PreviewViewModel.class);

        previewViewModel.getPhotos(view.getString(R.string.api_key), page).observe(view, this::showResult);
        previewViewModel.getSearchPhotos().observe(view, this::showResult);

        compositeDisposable.add(App.getInstance().getBus()
                .toObservable()
                .subscribe(text -> previewViewModel.search(view.getString(R.string.api_key), page, text)));
    }

    private void showResult(ResponseDto responseDto) {
        if (responseDto.stat.equals(OK_STATUS)) {
            List<String> urls = new ArrayList<>();
            for (PhotoDto photo : responseDto.photos.photos) {
                urls.add("http://farm" + photo.farm + ".static.flickr.com/"
                        + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg");
            }

            view.showPictures(urls);
        } else {
            view.showError();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }

    @Override
    public void onClick(String photo, ImageView image) {
        router.showDetailed(photo, image);
    }
}