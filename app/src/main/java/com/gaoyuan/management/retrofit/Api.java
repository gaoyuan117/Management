package com.gaoyuan.management.retrofit;

import com.gaoyuan.management.base.HttpResult;
import com.gaoyuan.management.bean.CommomBean;
import com.gaoyuan.management.bean.DaiLingLiaoBean;
import com.gaoyuan.management.bean.EditBean;
import com.gaoyuan.management.bean.FaLiaoMingXiBean;
import com.gaoyuan.management.bean.FenBaoShangBean;
import com.gaoyuan.management.bean.FenLiaoMingXiBean;
import com.gaoyuan.management.bean.LoginBean;
import com.gaoyuan.management.bean.PicBean;
import com.gaoyuan.management.bean.PicBean2;
import com.gaoyuan.management.bean.ProjectFenLiaoMingXiBean;
import com.gaoyuan.management.bean.RegisterBean;
import com.gaoyuan.management.bean.RegisterListBean;
import com.gaoyuan.management.bean.ToSendBean;
import com.gaoyuan.management.bean.UserBean;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by admin on 2017/3/27.
 */

public interface Api {

    //上传营业执照图片
    @Multipart
    @POST("http://zblive.jzbwlkj.com/index.php?g=Appapi&m=auth&a=UploadQNpic")
    Observable<ResponseBody> uploadVideo(@Part MultipartBody.Part body
            /*, @Part("token") String token
            , @Part("uid") String uid*/);


    @GET("/app/toUserRegist")
    Observable<HttpResult<RegisterListBean>> registerList(@Query("userType") String userType);


    //修改头像
    @FormUrlEncoded
    @POST("app/ user/modHead")
    Observable<RegisterBean> modAvatar(@Field("token") String token, @Field("headportrait") String headportrait);

    //上传营业执照图片
    @Multipart
    @POST("servlet/uploadImage?saveType=userOther")
    Observable<PicBean> uploadPic(@Part("saveType\"; filename=\"image.jpg") RequestBody imgs);

    //上传头像
    @Multipart
    @POST("servlet/uploadImage?saveType=userTx")
    Observable<PicBean> uploadAvatar(@Part("saveType\"; filename=\"image.jpg") RequestBody imgs);

    //上传签名图片
    @Multipart
    @POST("servlet/uploadImage?saveType=sign")
    Observable<PicBean> uploadSign(@Part("saveType\"; filename=\"image.jpg") RequestBody imgs);

    @Multipart
    @POST("servlet/uploadImage?saveType=sign")
    Observable<PicBean2> uploadFiles(@PartMap() Map<String, RequestBody> maps);

    //确认收料图片
    @Multipart
    @POST("servlet/uploadImage?saveType=wzglShouliaoPics")
    Observable<PicBean> uploadQueRen(@Part("saveType\"; filename=\"image.jpg") RequestBody imgs);

    //注册
    @FormUrlEncoded
    @POST("/app/userRegist")
    Observable<RegisterBean> register(@FieldMap Map<String, Object> map);

    //登录
    @FormUrlEncoded
    @POST("/app/userLogin")
    Observable<LoginBean> login(@Field("name") String name, @Field("password") String passWord);

    //是否登录
    @FormUrlEncoded
    @POST("app/verUserLogined")
    Observable<RegisterBean> isLogin(@Field("token") String token, @Field("userId") String userId);

    //修改密码
    @FormUrlEncoded
    @POST("app/user/modPwd")
    Observable<RegisterBean> changePassword(@FieldMap Map<String, Object> map);

    //忘记密码
    @FormUrlEncoded
    @POST("app/forgetPWD")
    Observable<RegisterBean> forgetPassword(@FieldMap Map<String, Object> map);

    //用户信息
    @GET("app/user/index")
    Observable<UserBean> userInfo(@Query("token") String token);

    //修改手机号
    @FormUrlEncoded
    @POST("app/user/modTelPhone")
    Observable<RegisterBean> modPhone(@Field("token") String token, @Field("telPhone") String phone);


    //去发料
    @GET("app/user/ghs/wld/toSend")
    Observable<HttpResult<ToSendBean>> toSend(@Query("token") String token);

    //发料
    @FormUrlEncoded
    @POST("app/user/ghs/wld/send")
    Observable<RegisterBean> send(@FieldMap Map<String, Object> map);

    //发料明细
    @GET("app/user/ghs/wld/findMyAllWlds")
    Observable<HttpResult<FaLiaoMingXiBean>> findMyAllWlds(@QueryMap Map<String, Object> map);

    //未验收发料
    @GET("app/user/ghs/wld/findMyWaitReciveWlds")
    Observable<HttpResult<FaLiaoMingXiBean>> findMyWaitReciveWlds(@QueryMap Map<String, Object> map);

    //供货商搜索物料单
    @GET("app/user/ghs/wld/searchMyWlds")
    Observable<HttpResult<FaLiaoMingXiBean>> searchMyWlds(@QueryMap Map<String, Object> map);

    //供货商 — 删除物料单
    @FormUrlEncoded
    @POST("app/user/ghs/wld/del")
    Observable<RegisterBean> delete(@Field("token") String token, @Field("wuliaodanId") int id);

    //供货商 — 去修改物料单
    @GET("app/user/ghs/wld/toMod")
    Observable<HttpResult<EditBean>> toMod(@Query("token") String token, @Query("wuliaodanId") String id);

    //供货商 — 去修改物料单
    @FormUrlEncoded
    @POST("app/user/ghs/wld/mod")
    Observable<RegisterBean> edit(@FieldMap Map<String, Object> map);

    //物资管理人员 - 待收料
    @GET("app/user/wlgl/wld/findWaitReciveWldsOfMyProject")
    Observable<HttpResult<FaLiaoMingXiBean>> findWaitReciveWldsOfMyProject(@QueryMap Map<String, Object> map);

    //物资管理人员 - 收料
    @FormUrlEncoded
    @POST("app/user/wlgl/wld/recive")
    Observable<RegisterBean> recive(@FieldMap Map<String, Object> map);

    //物资管理人员 - 收料明细
    @GET("app/user/wlgl/wld/findRecivedWldsOfMyProject")
    Observable<HttpResult<FaLiaoMingXiBean>> findRecivedWldsOfMyProject(@QueryMap Map<String, Object> map);

    //物资管理人员 - 未分完物料
    @GET("app/user/wlgl/wld/findRecivedButNotFenwanWldsOfMyProject")
    Observable<HttpResult<FaLiaoMingXiBean>> findRecivedButNotFenwanWlds(@QueryMap Map<String, Object> map);

    //物资管理人员 - 去分料
    @GET("app/user/wlgl/fld/toFenliao")
    Observable<HttpResult<FaLiaoMingXiBean>> toFenliao(@Query("token") String token, @Query("wuliaodanId") int id);

    //物资管理人员 - 分包商集合
    @GET("app/user/wlgl/fld/findFenbaoshangUsersByProject")
    Observable<HttpResult<FenBaoShangBean>> findFenbaoshangUsersByProject(@Query("token") String token);

    //物资管理人员 - 分料
    @FormUrlEncoded
    @POST("app/user/wlgl/fld/fenliao")
    Observable<RegisterBean> fenliao(@FieldMap Map<String, Object> map);

    //物资管理人员 - 分料明细
    @GET("app/user/wlgl/fld/findFldsByProjectId")
    Observable<HttpResult<FenLiaoMingXiBean>> findFldsByProjectId(@QueryMap Map<String, Object> map);

    //物资管理人员 - 某一个物料单分料明细
    @GET("app/user/wlgl/fld/findFldsByWldId")
    Observable<HttpResult<ProjectFenLiaoMingXiBean>> findFldsByWldId(@QueryMap Map<String, Object> map);

    //物资管理人员 - 未分完物料
    @GET("app/user/wlgl/wld/findRecivedButNotFenwanWldsOfMyProject")
    Observable<HttpResult<FenLiaoMingXiBean>> findRecivedButNotFenwan(@QueryMap Map<String, Object> map);

    //分包商 - 待领料
    @GET("app/user/fbs/fld/findMyWaitReciveFlds")
    Observable<HttpResult<DaiLingLiaoBean>> findMyWaitReciveFlds(@QueryMap Map<String, Object> map);

    //分包商 - 领料明细
    @GET("app/user/fbs/fld/findMyRecivedFlds")
    Observable<HttpResult<DaiLingLiaoBean>> findMyRecivedFlds(@QueryMap Map<String, Object> map);

    //分包商 - 确认领料
    @FormUrlEncoded
    @POST("app/user/fbs/fld/fenbaoshangRecive")
    Observable<RegisterBean> fenbaoshangRecive(@FieldMap Map<String, Object> map);
}
