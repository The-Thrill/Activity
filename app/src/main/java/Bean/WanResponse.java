package Bean;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WanResponse implements Serializable {

    private static final long serialVersionUID = 3540015569786464591L;
    private Data data;
    private int errorCode;
    private String errorMsg;

    //使用@Expose时，Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    //serialize:是否参与序列化  deserialize：是否参与反序列化
//    @Expose(serialize = false, deserialize = false)
//    private int  i;

    //i2不参与序列化与反序列化
//    private transient int i2;

    //无法以class作为字段名
//    @SerializedName("class")
//    private int cls;

    public void setWanData(Data data) {
        this.data = data;
    }

    public Data getWanData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "WanResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

}