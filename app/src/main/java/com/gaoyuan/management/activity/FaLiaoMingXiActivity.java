package com.gaoyuan.management.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gaoyuan.management.R;
import com.gaoyuan.management.adapter.FaLiaoMingXiAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.RegisterBean;
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
import io.reactivex.functions.Consumer;

public class FaLiaoMingXiActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv_faliaomingxi)
    RecyclerView mRecyclerView;
    @BindView(R.id.sr_faliaomingxi)
    SwipeRefreshLayout refreshLayout;

    private FaLiaoMingXiAdapter mAdapter;
    private List<FaLiaoMingXiBean.WuliaodanListBean> mList;

    private String startTime, endTime;
    private int type;//时间 1.开始时间 2.结束时间
    private int pageIndex = 1, searchpage = 1;//当前页
    private FaLiaoMingXiBean mFaLiaoMingXiBean;
    private String loadType = "normal";//加载数据的方式 normal 正常加载 search
    private TextView tvStartTime;
    private TextView tvEndTime;
    private EditText bianhao;
    private Dialog dialog;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_fa_liao_ming_xi);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("发料明细");
        setImg_right(R.mipmap.search)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSearchDialog();
                    }
                });

        mList = new ArrayList<>();
        mAdapter = new FaLiaoMingXiAdapter(R.layout.item_faliaomingxi, mList);
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
        mAdapter.disableLoadMoreIfNotFullPage();
        refreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    /**
     * 显示搜索框
     */
    private void showSearchDialog() {
        View view = View.inflate(this, R.layout.dialog_search, null);
        tvStartTime = (TextView) view.findViewById(R.id.tv_search_starttime);
        tvEndTime = (TextView) view.findViewById(R.id.tv_search_endtime);
        bianhao = (EditText) view.findViewById(R.id.et_search_bianhao);
        ImageView chongzhi = (ImageView) view.findViewById(R.id.img_search_chongzhi);
        ImageView chaxun = (ImageView) view.findViewById(R.id.img_search_chaxun);

        dialog = new Dialog(this, R.style.dialog_type);
        dialog.setContentView(view);
        dialog.show();

        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                dateDialog();
            }
        });

        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 2;
                dateDialog();
            }
        });

        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvEndTime.setText("选择日期");
                tvStartTime.setText("选择日期");
                startTime = "";
                endTime = "";
                bianhao.setText("");
            }
        });

        chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMyWlds(bianhao.getText().toString(), "");
            }
        });
    }

    /**
     * 日期对话框
     */
    private void dateDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (type == 1) {
                    startTime = date;
                    tvStartTime.setText(date);
                } else {
                    endTime = date;
                    tvEndTime.setText(date);
                }
                LogUtil.e("您选择了：" + date);
            }
        }, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(true);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    /**
     * 获取所有的发料信息
     */
    private void findMyAllWlds(String pageMethod) {
        loadType = "normal";
        map.clear();
        map.put("token", Config.token);
        if (!TextUtils.isEmpty(pageMethod)) {
            map.put("pageMethod", pageMethod);
        }
        if (pageIndex > 1) {
            map.put("currentPage", pageIndex);
        }
        RetrofitClient.getInstance().createApi().findMyAllWlds(map)
                .compose(RxUtils.<HttpResult<FaLiaoMingXiBean>>io_main())
                .subscribe(new BaseObjObserver<FaLiaoMingXiBean>(this, refreshLayout, mAdapter) {

                    @Override
                    protected void onHandleSuccess(FaLiaoMingXiBean faLiaoMingXiBean) {
                        if (faLiaoMingXiBean.getWuliaodanList() == null || faLiaoMingXiBean.getWuliaodanList().size() == 0) {
                            mAdapter.loadMoreEnd();
//                            mAdapter.setEnableLoadMore(false);
                            return;
                        }
                        if (pageIndex == 1) {
                            mList.clear();
                        }
                        mFaLiaoMingXiBean = faLiaoMingXiBean;
                        mList.addAll(faLiaoMingXiBean.getWuliaodanList());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.loadMoreComplete();
                    }
                });
    }


    /**
     * 供货商搜索物料单
     */
    private void searchMyWlds(String no, String pageMethod) {
        loadType = "search";
        Map<String, Object> map = new HashMap<>();
        if (!TextUtils.isEmpty(startTime)) {
            map.put("beginSendDate", startTime);
        }
        if (!TextUtils.isEmpty(endTime)) {
            map.put("endSendDate", endTime);
        }
        if (!TextUtils.isEmpty(no)) {
            map.put("sendMarkedNum", no);
        }
        map.put("token", Config.token);
        if (!TextUtils.isEmpty(pageMethod)) {
            map.put("pageMethod", pageMethod);
        }
        if (searchpage > 1) {
            map.put("currentPage", searchpage);
        }
        dialog.dismiss();
        RetrofitClient.getInstance().createApi().searchMyWlds(map)
                .compose(RxUtils.<HttpResult<FaLiaoMingXiBean>>io_main())
                .subscribe(new BaseObjObserver<FaLiaoMingXiBean>(this, "加载中") {
                    @Override
                    protected void onHandleSuccess(FaLiaoMingXiBean faLiaoMingXiBean) {
                        if (faLiaoMingXiBean.getWuliaodanList().size() == 0) {
                            showToastMsg("没有信息了");
                            mAdapter.setEnableLoadMore(false);
                            return;
                        }
                        if (searchpage == 1) {
                            mList.clear();
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
        if (loadType.equals("normal")) {
            pageIndex++;
            Log.e("gy", "加载更多");
            findMyAllWlds("number");
        } else {
            searchpage++;
            searchMyWlds("", "number");
        }

    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        searchpage = 1;
        mAdapter.setEnableLoadMore(true);
        findMyAllWlds("");
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
        }
        return false;
    }
}
