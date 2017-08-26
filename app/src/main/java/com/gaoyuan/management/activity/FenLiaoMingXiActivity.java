package com.gaoyuan.management.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.FaLiaoMingXiAdapter;
import com.gaoyuan.management.adapter.FenLiaoMingXiAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.FenLiaoMingXiBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.GlideUtil;
import com.gaoyuan.management.util.LogUtil;
import com.gaoyuan.management.view.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FenLiaoMingXiActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_faliaomingxi)
    RecyclerView mRecyclerView;
    @BindView(R.id.sr_faliaomingxi)
    SwipeRefreshLayout refreshLayout;

    private FenLiaoMingXiAdapter mAdapter;
    private List<FenLiaoMingXiBean.FenliaodanListBean> mList;

    private int pageIndex = 1;//当前页
    private FenLiaoMingXiBean mFenLiaoMingXiBean;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_fa_liao_ming_xi);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("分料明细");

        mList = new ArrayList<>();
        mAdapter = new FenLiaoMingXiAdapter(R.layout.item_fenliaomingxi, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 15, ContextCompat.getColor(getActivity(), R.color.white)));
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initData() {
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
        RetrofitClient.getInstance().createApi().findFldsByProjectId(map)
                .compose(RxUtils.<HttpResult<FenLiaoMingXiBean>>io_main())
                .subscribe(new BaseObjObserver<FenLiaoMingXiBean>(this, refreshLayout, mAdapter) {

                    @Override
                    protected void onHandleSuccess(FenLiaoMingXiBean faLiaoMingXiBean) {
                        if (faLiaoMingXiBean.getFenliaodanList().size() == 0) {
                            mAdapter.setEnableLoadMore(false);
                            return;
                        }
                        if (pageIndex == 1) {
                            mList.clear();
                        }
                        mFenLiaoMingXiBean = faLiaoMingXiBean;
                        mList.addAll(faLiaoMingXiBean.getFenliaodanList());
                        mAdapter.setProjectName(faLiaoMingXiBean.getProject().getName());
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
        mAdapter.setEnableLoadMore(true);
        findMyAllWlds("");
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.img_fenliaomingxi_ling://查看收料人签名大图
                String signSend = mList.get(position).getSignFenbaoshang();
                if (!TextUtils.isEmpty(signSend)) {
                    List<String> list1 = new ArrayList<>();
                    list1.add(signSend);
                    GlideUtil.imageBrower(this, 0, list1, "net");
                }
                break;
        }
        return false;
    }
}
