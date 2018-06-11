package ru.taximaster.testapp.presentation.module.map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.data.dto.PhotoDto;
import ru.taximaster.testapp.data.dto.ResponseDto;
import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BaseFragment;
import ru.taximaster.testapp.presentation.util.PresenterInjector;

import static ru.taximaster.testapp.presentation.util.Const.EXTRA;

public class MapFragment extends BaseFragment<MapPresenter> implements MapView {

    public static MapFragment newInstance(ResponseDto responseDto) {
        MapFragment fragment = new MapFragment();
        Bundle extras = new Bundle();
        extras.putParcelable(EXTRA, responseDto);
        fragment.setArguments(extras);
        return fragment;
    }

    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;

    private ResponseDto response;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onStart();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map, container, false);

        //noinspection ConstantConditions
        response = getArguments().getParcelable(EXTRA);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {

            }
        });
        for (PhotoDto photo : response.photos.photos) {
            String url = "http://farm" + photo.farm + ".static.flickr.com/"
                    + photo.server + "/" + photo.id + "_" + photo.secret + ".jpg";

            Picasso.with(getContext())
                    .load(url)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(photo.latitude, photo.longitude))
                                    .title(photo.title)
                                    .icon(BitmapDescriptorFactory.fromBitmap(scaleBitmap(bitmap))));
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                        }
                    });


        }

//        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private Bitmap scaleBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        if (width > height) {
            // landscape
            float ratio = (float) width / MAX_WIDTH;
            width = MAX_WIDTH;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / MAX_HEIGHT;
            height = MAX_HEIGHT;
            width = (int) (width / ratio);
        } else {
            // square
            height = MAX_HEIGHT;
            width = MAX_WIDTH;
        }

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

    @Override
    protected MapPresenter injectPresenter() {
        return PresenterInjector.provideMapPresenter(this, (MainRouter) getActivity());
    }
}