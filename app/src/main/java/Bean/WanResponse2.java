package Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class WanResponse2 implements Parcelable {

    private Data2 data;
    private int code;
    private String message;

    public WanResponse2() {
    }

    public WanResponse2(Data2 data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    protected WanResponse2(Parcel in) {
        // 读取对象需要提供一个类加载器去读取,因为写入的时候写入了类的相关信息
        data = in.readParcelable(Data2.class.getClassLoader());
        code = in.readInt();
        message = in.readString();
    }

    /**
     * 序列化
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化对象的时候传入要序列化的对象和一个flag,
        // 这里的flag几乎都是0,除非标识当前对象需要作为返回值返回,不能立即释放资源
        dest.writeParcelable((Parcelable) data, 0);
        dest.writeInt(code);
        dest.writeString(message);
    }


    /**
     * 负责反序列化
     */
    public static final Creator<WanResponse2> CREATOR = new Creator<WanResponse2>() {
        /**
         * 从序列化对象中，获取原始的对象
         * @param in
         * @return
         */
        @Override
        public WanResponse2 createFromParcel(Parcel in) {
            return new WanResponse2(in);
        }

        /**
         * 创建指定长度的原始对象数组
         * @param size
         * @return
         */
        @Override
        public WanResponse2[] newArray(int size) {
            return new WanResponse2[size];
        }
    };

    /**
     * 描述
     * 返回的是内容的描述信息
     * 只针对一些特殊的需要描述信息的对象,需要返回1,其他情况返回0就可以
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public Data2 getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static Creator<WanResponse2> getCREATOR() {
        return CREATOR;
    }

    public void setData(Data2 data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WanResponse2{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
