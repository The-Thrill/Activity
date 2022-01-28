package Core;

import Bean.ResponseResult;
import Bean.SuccessBean;
import io.reactivex.rxjava3.core.Observable;

public class LoginEngine {

    public static Observable<ResponseResult> login(String name, String pwd) {

        ResponseResult responseResult = new ResponseResult();

        if ("Derry".equals(name) && "123456".equals(pwd)) {
            SuccessBean successBean = new SuccessBean();
            successBean.setId(1233);
            successBean.setName("Derry登录成功");
            responseResult.setData(successBean);
            responseResult.setCode(200);
            responseResult.setMessage("登录成功");
        }else {
            responseResult.setData(null);
            responseResult.setCode(404);
            responseResult.setMessage("登录失败");
        }
        return Observable.just(responseResult);
    }
}
