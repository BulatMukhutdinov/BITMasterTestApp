package ru.taximaster.testapp.presentation.module.preview;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import ru.taximaster.testapp.App;
import ru.taximaster.testapp.data.dto.ResponseDto;
import ru.taximaster.testapp.data.network.FlickrApi;


public class PreviewViewModel extends ViewModel {
    private static final int PAGE_SIZE = 20;

    private final CompositeDisposable compositeDisposable;

    private MutableLiveData<ResponseDto> photos;

    @Getter
    private MutableLiveData<ResponseDto> searchPhotos;

    public PreviewViewModel() {
        compositeDisposable = new CompositeDisposable();
        photos = new MutableLiveData<>();
        searchPhotos = new MutableLiveData<>();
    }

    public MutableLiveData<ResponseDto> getPhotos(String apiKey, int page) {
        if (photos.getValue() == null) {
            compositeDisposable.add(getDisposable(apiKey, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(photos::setValue));
        }
        return photos;
    }

    public void search(String apiKey, int page, String text) {
        compositeDisposable.add(FlickrApi.newCall().findPictures(apiKey, PAGE_SIZE, page, text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchPhotos::setValue));
    }

    private Single<ResponseDto> getDisposable(String apiKey, int page) {
        if (App.getInstance().getSearchText() == null || App.getInstance().getSearchText().isEmpty()) {
            return FlickrApi.newCall().pictures(apiKey, PAGE_SIZE, page);
        }
        return FlickrApi.newCall().findPictures(apiKey, PAGE_SIZE, page, App.getInstance().getSearchText());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
