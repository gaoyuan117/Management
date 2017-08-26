package com.gaoyuan.management.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.DaiLingLiaoAdapter;
import com.gaoyuan.management.adapter.DaiShouLiaoAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.DaiLingLiaoBean;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.GlideUtil;
import com.gaoyuan.management.view.RecyclerViewDivider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaiLingLiaoActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv_daishouliao)
    RecyclerView mRecyclerView;
    @BindView(R.id.sr_daishouliao)
    SwipeRefreshLayout mRefreshLayout;

    private DaiLingLiaoAdapter mAdapter;
    private List<DaiLingLiaoBean.FenliaodanListBean> mList;

    private int pageIndex = 1;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_dai_shou_liao);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("待领料");
        mList = new ArrayList<>();
        mAdapter = new DaiLingLiaoAdapter(R.layout.item_dailingliao, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, ContextCompat.getColor(getActivity(), R.color.white)));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pageIndex = 1;
        findWaitReciveWldsOfMyProject("");
    }

    private void findWaitReciveWldsOfMyProject(String pageMethod) {
        map.clear();
        map.put("token", Config.token);
        if (!TextUtils.isEmpty(pageMethod)) {
            map.put("pageMethod", pageMethod);
        }
        if (pageIndex > 1) {
            map.put("currentPage", pageIndex);
        }
        RetrofitClient.getInstance().createApi().findMyWaitReciveFlds(map)
                .compose(RxUtils.<HttpResult<DaiLingLiaoBean>>io_main())
                .subscribe(new BaseObjObserver<DaiLingLiaoBean>(this, mRefreshLayout, mAdapter) {
                    @Override
                    protected void onHandleSuccess(DaiLingLiaoBean faLiaoMingXiBean) {
                        if (pageIndex == 1) {
                            mList.clear();
                        }

                        if (faLiaoMingXiBean.getFenliaodanList().size() == 0) {
                            mAdapter.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }

                        mList.addAll(faLiaoMingXiBean.getFenliaodanList());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.loadMoreComplete();

                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        findWaitReciveWldsOfMyProject("number");
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        mAdapter.setEnableLoadMore(true);
        findWaitReciveWldsOfMyProject("");
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {

            case R.id.tv_dailingliao_tolingliao://去领料
                Intent intent = new Intent(this, QueRenLingLiaoActivity.class);
                intent.putExtra("id", mList.get(position).getId() + "");
                startActivity(intent);
                break;
        }
        return false;
    }
}