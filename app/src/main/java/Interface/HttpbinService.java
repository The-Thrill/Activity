package Interface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HttpbinService {

    @POST("user/login")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username") String userName, @Field("password") String pwd);

    @GET("user_article/list/{id}/json")
    Call<ResponseBody> get(@Path("id") int page);
}
