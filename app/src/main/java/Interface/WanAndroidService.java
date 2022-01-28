package Interface;


import Bean.WanResponse;
import io.reactivex.rxjava3.core.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WanAndroidService {

    @POST("user/login")
    @FormUrlEncoded
    Call<ResponseBody> loginGson(@Field("username") String userName, @Field("password") String pwd);

    @POST("user/login")
    @FormUrlEncoded
    Call<WanResponse> loginGsonConverter(@Field("username") String userName, @Field("password") String pwd);

    @POST("user/login")
    @FormUrlEncoded
    Flowable<WanResponse> loginRxjava(@Field("username") String userName, @Field("password") String pwd);

    @GET("lg/collect/list/{pageNum}/json")
    Flowable<ResponseBody> getArticle(@Path("pageNum") int pageNum);
}
