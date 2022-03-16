package Bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Data2 implements Parcelable {

    private boolean admin;
    private List<String> chapterTops;
    private int coinCount;
    private List<Integer> collectIds;
    private String email;
    private String icon;
    private long id;
    private String nickname;
    private String password;
    private String publicName;
    private String token;
    private int type;
    private String username;

    protected Data2(Parcel in) {
        admin = in.readByte() != 0;
        chapterTops = in.createStringArrayList();
        coinCount = in.readInt();
        email = in.readString();
        icon = in.readString();
        id = in.readLong();
        nickname = in.readString();
        password = in.readString();
        publicName = in.readString();
        token = in.readString();
        type = in.readInt();
        username = in.readString();
    }

    public static final Creator<Data2> CREATOR = new Creator<Data2>() {
        @Override
        public Data2 createFromParcel(Parcel in) {
            return new Data2(in);
        }

        @Override
        public Data2[] newArray(int size) {
            return new Data2[size];
        }
    };

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setChapterTops(List<String> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<String> getChapterTops() {
        return chapterTops;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Data{" +
                "admin=" + admin +
                ", chapterTops=" + chapterTops +
                ", coinCount=" + coinCount +
                ", collectIds=" + collectIds +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", publicName='" + publicName + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (admin ? 1 : 0));
        dest.writeStringList(chapterTops);
        dest.writeInt(coinCount);
        dest.writeString(email);
        dest.writeString(icon);
        dest.writeLong(id);
        dest.writeString(nickname);
        dest.writeString(password);
        dest.writeString(publicName);
        dest.writeString(token);
        dest.writeInt(type);
        dest.writeString(username);
    }
}
