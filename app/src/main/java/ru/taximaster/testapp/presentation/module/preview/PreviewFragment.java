package ru.taximaster.testapp.presentation.module.preview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.taximaster.testapp.App;
import ru.taximaster.testapp.R;
import ru.taximaster.testapp.databinding.PreviewBinding;
import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BaseFragment;
import ru.taximaster.testapp.presentation.module.preview.photos.PhotosFragment;
import ru.taximaster.testapp.presentation.util.PresenterInjector;

public class PreviewFragment extends BaseFragment<PreviewPresenter> implements PreviewView {
    private PreviewBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.preview, container, false);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        binding.results.setAdapter(pagerAdapter);

        binding.searchButton.setOnClickListener(v -> {
            App.getInstance().setSearchText(binding.searchText.getText().toString());
            App.getInstance().getBus().post(binding.searchText.getText().toString());
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onStart();
    }

    @Override
    protected PreviewPresenter injectPresenter() {
        return PresenterInjector.providePreviewPresenter(this, (MainRouter) getActivity());
    }


    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        private static final int PAGE_COUNT = 4;

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotosFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

}