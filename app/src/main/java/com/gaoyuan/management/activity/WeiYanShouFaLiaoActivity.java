package com.gaoyuan.management.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.gaoyuan.management.adapter.WeiYanShouFaLiaoAdapter;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.BaseObjObserver;
import com.gaoyuan.management.rxjava.RxUtils;
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

public class WeiYanShouFaLiaoActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.rv_weiyanshoufaliao)
    RecyclerView mRecyclerView;

    @BindView(R.id.sr_weiyanshoufaliao)
    SwipeRefreshLayout refreshLayout;

    private TextView tvStartTime;
    private TextView tvEndTime;
    private EditText bianhao;
    private Dialog dialog;

    private WeiYanShouFaLiaoAdapter mAdapter;
    private List<FaLiaoMingXiBean.WuliaodanListBean> mList;

    private String startTime, endTime;
    private int type;//时间 1.开始时间 2.结束时间
    private int pageIndex = 1, searchpage = 1;//当前页
    private FaLiaoMingXiBean mFaLiaoMingXiBean;
    private String loadType = "normal";//加载数据的方式 normal 正常加载 search

    @Override
    public void setContent() {
        setContentView(R.layout.activity_wei_yan_shou_fa_liao);
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        setCenterTitle("未验收发料");
        setImg_right(R.mipmap.search)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSearchDialog();
                    }
                });

        mList = new ArrayList<>();
        mAdapter = new WeiYanShouFaLiaoAdapter(R.layout.item_weiyanshoufaliao, mList);
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
        findMyWaitReciveWlds("");
    }

    @Override
    public void initEvent() {
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);

        refreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    /**
     * 获取未验收发料
     */
    private void findMyWaitReciveWlds(String pageMethod) {
        loadType = "normal";
        map.clear();
        map.put("token", Config.token);
        if (!TextUtils.isEmpty(pageMethod)) {
            map.put("pageMethod", pageMethod);
        }
        if (pageIndex > 1) {
            map.put("currentPage", pageIndex);
        }
        RetrofitClient.getInstance().createApi().findMyWaitReciveWlds(map)
                .compose(RxUtils.<HttpResult<FaLiaoMingXiBean>>io_main())
                .subscribe(new BaseObjObserver<FaLiaoMingXiBean>(this, refreshLayout, mAdapter) {
                    @Override
                    protected void onHandleSuccess(FaLiaoMingXiBean faLiaoMingXiBean) {
                        if (pageIndex == 1) {
                            mList.clear();
                        }
                        if (faLiaoMingXiBean.getWuliaodanList().size() == 0) {
                            mAdapter.setEnableLoadMore(false);
                            mAdapter.notifyDataSetChanged();
                            return;
                        }

                        mList.addAll(faLiaoMingXiBean.getWuliaodanList());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.loadMoreComplete();
                    }
                });
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
            findMyWaitReciveWlds("number");
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
        findMyWaitReciveWlds("");
    }

    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {

        switch (view.getId()) {
            case R.id.img_weiyanshoufaliao_edit:
                if (mList.get(position).getVoCanModOrDel().equals("YES")) {
                    Intent intent = new Intent(WeiYanShouFaLiaoActivity.this, SendEditdActivity.class);
                    intent.putExtra("id", mList.get(position).getId()+"");
                    startActivity(intent);
                } else {
                    showToastMsg("不可修改");
                }
                break;
            case R.id.img_weiyanshoufaliao_delete:
                showDeleteDialog(position);
                break;
        }

        return false;
    }


    /**
     * 删除对话框
     *
     * @param position
     */
    private void showDeleteDialog(final int position) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("是否删除该物料单?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(position, mList.get(position).getId());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 删除
     *
     * @param positon
     * @param id
     */
    private void delete(final int positon, int id) {
        RetrofitClient.getInstance().createApi().delete(Config.token, id)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            showToastMsg("删除成功");
                            mList.remove(positon);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            showToastMsg(registerBean.getErrorMessage());
                        }
                    }
                });
    }
}
