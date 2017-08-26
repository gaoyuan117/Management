package com.gaoyuan.management.activity;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gaoyuan.management.MyApplication;
import com.gaoyuan.management.R;
import com.gaoyuan.management.base.BaseActivity;
import com.gaoyuan.management.bean.FenLiaoMingXiBean;
import com.gaoyuan.management.bean.LoginBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.UserBean;
import com.gaoyuan.management.config.Config;
import com.gaoyuan.management.retrofit.RetrofitClient;
import com.gaoyuan.management.rxjava.RxUtils;
import com.gaoyuan.management.util.LogUtil;
import com.gaoyuan.management.util.SharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Field;

public class MainActivity extends BaseActivity {

    /**
     * 顶部个人信息
     */
    @BindView(R.id.img_main_avatar)
    CircleImageView mAvatar;
    @BindView(R.id.tv_main_name)
    TextView mName;
    @BindView(R.id.tv_main_phone)
    TextView mPhone;
    @BindView(R.id.ll_main_personal)
    LinearLayout mPersonal;

    /**
     * 供应商
     */
    @BindView(R.id.ll_main_supply_woyaofaliao)
    LinearLayout mSupplyWoyaofaliao;
    @BindView(R.id.ll_main_supply_weiyanshoufaliao)
    LinearLayout mSupplyWeiyanshoufaliao;
    @BindView(R.id.ll_main_supply_faliaomingxi)
    LinearLayout mSupplyFaliaomingxi;
    @BindView(R.id.ll_main_supply)
    LinearLayout llMainSupply;

    /**
     * 分包商
     */
    @BindView(R.id.ll_main_distribute_dailingliao)
    LinearLayout mDistributeDailingliao;
    @BindView(R.id.ll_main_distribute_lingliaomingxi)
    LinearLayout mDistributeLingliaomingxi;
    @BindView(R.id.ll_main_distribute)
    LinearLayout llMainDistribute;

    /**
     * 物资管理人员
     */
    @BindView(R.id.ll_main_manager_daishouliao)
    LinearLayout mManagerDaishouliao;
    @BindView(R.id.ll_main_manager_shouliaomingxi)
    LinearLayout mManagerShouliaomingxi;
    @BindView(R.id.ll_main_manager1)
    LinearLayout llMainManager1;
    @BindView(R.id.ll_main_manager_weifenwanwuliao)
    LinearLayout mManagerWeifenwanwuliao;
    @BindView(R.id.ll_main_manager_fenliaomingxi)
    LinearLayout mManagerFenliaomingxi;
    @BindView(R.id.ll_main_manager2)
    CardView llMainManager2;

    /**
     * 底部个人中心 密码修改 退出
     */
    @BindView(R.id.ll_main_personal2)
    LinearLayout mPersonal2;
    @BindView(R.id.ll_main_supply_mimaxiugai)
    LinearLayout mSupplyMimaxiugai;
    @BindView(R.id.bt_main_exit)
    Button mExit;

    @Override
    public void setContent() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void init() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin();
    }

    @OnClick({R.id.img_main_avatar, R.id.ll_main_personal, R.id.ll_main_supply_woyaofaliao, R.id.ll_main_supply_weiyanshoufaliao, R.id.ll_main_supply_faliaomingxi, R.id.ll_main_distribute_dailingliao, R.id.ll_main_distribute_lingliaomingxi, R.id.ll_main_manager_daishouliao, R.id.ll_main_manager_shouliaomingxi, R.id.ll_main_manager_weifenwanwuliao, R.id.ll_main_manager_fenliaomingxi, R.id.ll_main_personal2, R.id.ll_main_supply_mimaxiugai, R.id.bt_main_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_main_avatar://头像
                break;
            case R.id.ll_main_personal://顶部个人信息
                startAct(PersonalActivity.class);
                break;

            /**
             * 供应商
             */
            case R.id.ll_main_supply_woyaofaliao://供应商我要发料
                startAct(WoYaoFaLiaoActivity.class);
                break;
            case R.id.ll_main_supply_weiyanshoufaliao://供应商未验收发料
                startAct(WeiYanShouFaLiaoActivity.class);
                break;
            case R.id.ll_main_supply_faliaomingxi://供应商发料明细
                startAct(FaLiaoMingXiActivity.class);
                break;

            /**
             * 物资管理人员
             */
            case R.id.ll_main_manager_daishouliao://物资管理 待收料
                startAct(DaiShouLiaoActivity.class);
                break;

            case R.id.ll_main_manager_shouliaomingxi://物资管理 收料明细
                startAct(ShouLiaoMingXiActivity.class);
                break;

            case R.id.ll_main_manager_weifenwanwuliao://物资管理 未分完物料
                startAct(WeiFenWanActivity.class);
                break;

            case R.id.ll_main_manager_fenliaomingxi://物资管理 分料明细
                startAct(FenLiaoMingXiActivity.class);
                break;

            /**
             * 分包商
             */
            case R.id.ll_main_distribute_dailingliao://分包商 待领料
                startAct(DaiLingLiaoActivity.class);
                break;

            case R.id.ll_main_distribute_lingliaomingxi://分包商 领料明细
                startAct(LingLiaoMingXiActivity.class);
                break;

            /**
             * 底部
             */
            case R.id.ll_main_personal2://个人信息
                startAct(PersonalActivity.class);
                break;

            case R.id.ll_main_supply_mimaxiugai://密码修改
                startAct(ChangePasswordActivity.class);
                break;

            case R.id.bt_main_exit://退出
                new SharedPreference("LoginBean")
                        .edit()
                        .putString("token", "").commit();
                startAct(LoginActivity.class);
                finish();
                break;
        }
    }

    /**
     * 判断是否登录
     */
    private void isLogin() {
        RetrofitClient.getInstance().createApi().isLogin(Config.token, Config.userId)
                .compose(RxUtils.<RegisterBean>io_main())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        if (registerBean.getStatus().equals("ok")) {
                            if (registerBean.getResults().equals("nologin")) {
                                LogUtil.e("登录状态：" + registerBean.getResults());
                                showToastMsg("身份过期，请重新登录");
                                startAct(LoginActivity.class);
                                new SharedPreference("LoginBean").edit().putString("bean", "").commit();
                                finish();
                            } else {
                                getUserInfo();
                            }
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {
        RetrofitClient.getInstance().createApi().userInfo(Config.token)
                .compose(RxUtils.<UserBean>io_main())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        if (userBean.getStatus().equals("ok")) {
                            MyApplication.userBean = userBean;
                            setUserInfo();
                        }
                    }

                });
    }

    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        Glide.with(this).load(MyApplication
                .userBean.getResults().getHeadportrait())
                .error(R.mipmap.default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mAvatar);

        mName.setText(MyApplication.userBean.getResults().getNickName());
        mPhone.setText(MyApplication.userBean.getResults().getTelPhone());

        //根据登录角色 判断布局的显示隐藏
        String userType = MyApplication.userBean.getResults().getUserType();
        if (userType.equals("gonghuoshang")) {
            llMainSupply.setVisibility(View.VISIBLE);

        } else if (userType.equals("fenbaoshang")) {
            llMainDistribute.setVisibility(View.VISIBLE);

        } else if (userType.equals("wuziguanliren")) {
            llMainManager1.setVisibility(View.VISIBLE);
            llMainManager2.setVisibility(View.VISIBLE);
        }
    }


}
