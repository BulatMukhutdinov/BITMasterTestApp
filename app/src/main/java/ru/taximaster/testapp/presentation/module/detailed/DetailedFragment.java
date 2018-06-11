package ru.taximaster.testapp.presentation.module.detailed;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BaseFragment;
import ru.taximaster.testapp.presentation.util.PresenterInjector;

import static ru.taximaster.testapp.presentation.util.Const.EXTRA;
import static ru.taximaster.testapp.presentation.util.Const.TRANSITION_NAME;

public class DetailedFragment extends BaseFragment<DetailedPresenter> implements DetailedView {


    public static DetailedFragment newInstance(String photo, String transitionName) {
        DetailedFragment fragment = new DetailedFragment();
        Bundle extras = new Bundle();
        extras.putString(EXTRA, photo);
        extras.putString(TRANSITION_NAME, transitionName);
        fragment.setArguments(extras);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
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
    protected DetailedPresenter injectPresenter() {
        return PresenterInjector.provideDetailedPresenter(this, (MainRouter) getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detailed, container, false);
        ImageView image = view.findViewById(R.id.photo);

        if (getArguments() != null) {
            String photo = getArguments().getString(EXTRA);
            String transitionName = getArguments().getString(TRANSITION_NAME);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image.setTransitionName(transitionName);
            }

            //noinspection ConstantConditions
            Picasso.with(getContext())
                    .load(photo)
                    .noFade()
                    .placeholder(getResources().getDrawable(R.drawable.iv_ico))
                    .into(image, new Callback() {
                        @Override
                        public void onSuccess() {
                            startPostponedEnterTransition();
                        }

                        @Override
                        public void onError() {
                            startPostponedEnterTransition();
                        }
                    });
        }

        return view;
    }
}