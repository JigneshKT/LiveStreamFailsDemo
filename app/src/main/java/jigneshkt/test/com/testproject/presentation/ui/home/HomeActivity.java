package jigneshkt.test.com.testproject.presentation.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.base.BaseActivity;
import jigneshkt.test.com.testproject.domain.model.LiveStreamFail;
import jigneshkt.test.com.testproject.presentation.ui.home.adapter.LiveStreamsAdapter;


public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityView {


    @BindView(R.id.recyclerview_live_stream_fails)
    RecyclerView recyclerview_live_stream_fails;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;




    private LiveStreamsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Inject
    HomeActivityPresenter homeActivityPresenter;

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected HomeActivityPresenter getPresenter() {
        return homeActivityPresenter;
    }

    @Override
    protected void configureViews() {
        super.configureViews();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerview_live_stream_fails.setLayoutManager(mLayoutManager);

        mAdapter = new LiveStreamsAdapter(new ArrayList<LiveStreamFail>());
        recyclerview_live_stream_fails.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onLiveStreamSuccess(ArrayList<LiveStreamFail> liveStreams) {
        mAdapter.addAll(liveStreams);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onLiveStreamFailure() {
        progressbar.setVisibility(View.GONE);
        Toast.makeText(this,getString(R.string.error_msg),Toast.LENGTH_LONG).show();
    }
}
