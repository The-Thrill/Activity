package Utils;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.activity.R;

@GlideExtension
public class MyGlideExtension {

   private MyGlideExtension() {}

   /***
    * 全局统一配置
    * @param requestOptions
    */
   @GlideOption
   public static BaseRequestOptions<?> injectOptions(BaseRequestOptions<?> requestOptions) {
      return requestOptions.placeholder(R.mipmap.ic_launcher)
              .error(R.mipmap.ic_launcher_round)
              .circleCrop();
   }
}
