package com.gaoyuan.management.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.ShouLiaoMingXiAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
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

public class WeiFenWanActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_faliaomingxi)
    RecyclerView mRecyclerView;
    @BindView(R.id.sr_faliaomingxi)
    SwipeRefreshLayout refreshLayout;

    private ShouLiaoMingXiAdapter mAdapter;
    private List<FaLiaoMingXiBean.WuliaodanListBean> mList;

    private int pageIndex = 1;//当前页
    private FaLiaoMingXiBean mFaLiaoMingXiBean;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_fa_liao_ming_xi);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("未分完物料");

        mList = new ArrayList<>();
        mAdapter = new ShouLiaoMingXiAdapter(R.layout.item_shouliaoming, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, ContextCompat.getColor(getActivity(), R.color.white)));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        pageIndex = 1;
        findMyAllWlds("");
    }

    @Override
    public void initEvent() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        refreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    /**
     * 获取所有的发料信息
     */
    private void findMyAllWlds(String pageMethod) {
        map.clear();
        map.put("token", Config.token);
        if (!TextUtils.isEmpty(pageMethod)) {
            map.put("pageMethod", pageMethod);
        }
        if (pageIndex > 1) {
            map.put("currentPage", pageIndex);
        }
        RetrofitClient.getInstance().createApi().findRecivedButNotFenwanWlds(map)
                .compose(RxUtils.<HttpResult<FaLiaoMingXiBean>>io_main())
                .subscribe(new BaseObjObserver<FaLiaoMingXiBean>(this, refreshLayout, mAdapter) {

                    @Override
                    protected void onHandleSuccess(FaLiaoMingXiBean faLiaoMingXiBean) {
                        if (pageIndex == 1) {
                            mList.clear();
                        }
                        if (faLiaoMingXiBean.getWuliaodanList().size() == 0) {
                            mAdapter.notifyDataSetChanged();
                            mAdapter.setEnableLoadMore(false);
                            return;
                        }

                        mFaLiaoMingXiBean = faLiaoMingXiBean;
                        mList.addAll(faLiaoMingXiBean.getWuliaodanList());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.loadMoreComplete();
                    }
                });
    }


    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        findMyAllWlds("number");
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        findMyAllWlds("");
        mAdapter.setEnableLoadMore(true);

    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.img_faliaomingxi_fa://查看发料人签名大图
                String signSend = mList.get(position).getSignSend();
                if (!TextUtils.isEmpty(signSend)) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(signSend);
                    GlideUtil.imageBrower(this, 0, list1, "net");
                }
                break;

            case R.id.img_faliaomingxi_shou://查看收料人签名大图
                String signReceive = mList.get(position).getSignRecive();
                if (!TextUtils.isEmpty(signReceive)) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(signReceive);
                    GlideUtil.imageBrower(this, 0, list1, "net");
                }
                break;

            case R.id.img_shouliao_chakan://查看分料明细
                Intent intent2 = new Intent(this, ProjectFenLiaoMingXiActivity.class);
                intent2.putExtra("id", mList.get(position).getId() + "");
                startActivity(intent2);
                break;

            case R.id.img_shouliao_fenliao://分料
                Intent intent = new Intent(this, FenLiaoActivity.class);
                intent.putExtra("list", (Serializable) mList.get(position).getVoWuliaodanItemList());
                intent.putExtra("sedTime", mList.get(position).getCreateDateStr());
                intent.putExtra("recTime", mList.get(position).getReciveDateStr());
                intent.putExtra("id", mList.get(position).getId() + "");
                intent.putExtra("gonghuoshang", mList.get(position).getVoGonghuoshangUser().getName());
                startActivity(intent);
                break;
        }
        return false;
    }
}
