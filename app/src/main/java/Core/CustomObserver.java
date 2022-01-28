package Core;

import Bean.ResponseResult;
import Bean.SuccessBean;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class CustomObserver implements Observer<ResponseResult> {

    public abstract void success(SuccessBean successBean);
    public abstract void error(String message);


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull ResponseResult responseResult) {
        if(responseResult.getData() == null) {
            error(responseResult.getMessage() + "请求失败，请检查。。。");
        }else {
            success(responseResult.getData());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        error(e.getMessage() + "请求失败，错误详情");
    }

    @Override
    public void onComplete() {

    }
}
