package ru.taximaster.testapp.data.network.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.taximaster.testapp.data.dto.ResponseDto;

public interface FlickrApiService {

    @GET("services/rest/?method=flickr.photos.search&sort=relevance&content_type=1&media=photos&format=json&nojsoncallback=1&extras=geo&has_geo=1")
    Single<ResponseDto> pictures(@Query("api_key") String apiKey, @Query("per_page") int perPage,
                                 @Query("page") int page);

    @GET("services/rest/?method=flickr.photos.search&sort=relevance&content_type=1&media=photos&format=json&nojsoncallback=1&extras=geo&has_geo=1")
    Single<ResponseDto> findPictures(@Query("api_key") String apiKey, @Query("per_page") int perPage,
                                     @Query("page") int page, @Query("text") String searchText);
}