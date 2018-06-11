package ru.taximaster.testapp.presentation.module.preview.photos;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ru.taximaster.testapp.R;
import ru.taximaster.testapp.databinding.PreviewGridBinding;
import ru.taximaster.testapp.presentation.MainRouter;
import ru.taximaster.testapp.presentation.module.base.BaseFragment;
import ru.taximaster.testapp.presentation.util.PresenterInjector;

import static ru.taximaster.testapp.presentation.util.Const.EXTRA;

public class PhotosFragment extends BaseFragment<PhotosPresenter> implements PhotosView {

    public static PhotosFragment newInstance(int position) {
        PhotosFragment fragment = new PhotosFragment();
        Bundle extras = new Bundle();
        extras.putInt(EXTRA, position);
        fragment.setArguments(extras);
        return fragment;
    }

    private PreviewGridBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //noinspection ConstantConditions
        int pageNumber = getArguments().getInt(EXTRA);

        binding.pageNumber.setText(getString(R.string.page_number, pageNumber));

        presenter.onStart(pageNumber);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.preview_grid, container, false);

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map:
                presenter.mapMenuClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected PhotosPresenter injectPresenter() {
        return PresenterInjector.providePreviewPresenter(this, (MainRouter) getActivity());
    }


    @Override
    public void showPictures(List<String> photos) {
        binding.progressBar.setVisibility(View.GONE);
        GridAdapter adapter = new GridAdapter(photos, presenter);
        binding.photos.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), getString(R.string.general_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(int title, int message) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }

}