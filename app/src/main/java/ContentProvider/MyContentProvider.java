package ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import Utils.LogUtils;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";

    //Uri解析
    //1、UriMatcher:在ContentProvider创建时，创定好匹配规则，当调用了ContentProvider中的方法时
    //利用匹配类去匹配传的uri,根据不同的uri给出不同的处理
    //2、Uri自带的解析方法
    private UriMatcher uriMatcher;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        LogUtils.i(TAG, "delete--uri "+ uri);
        int delete = 0;

        //uriMatcher匹配成功返回Code
        int code = uriMatcher.match(uri);
        if(code == 1000) {
            LogUtils.i(TAG, "uriMatcher--success");
            delete = -1;
        }else {
            LogUtils.i(TAG, "uriMatcher--fail");
            delete = sqLiteDatabase.delete("info_tb", selection, selectionArgs);
        }
        return delete;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        LogUtils.i(TAG, "insert--uri "+ uri + "--values "+ values);
        if (values.size() <= 0) {
            String authority = uri.getAuthority();
            String path = uri.getPath();
            String query = uri.getQuery();
            String name = uri.getQueryParameter("name");
            LogUtils.i(TAG, "authority = " + authority + ",path = " + path +
                    ",query = " + query + ",name = " + name);
            values.put("name", name);
        }
        long id = sqLiteDatabase.insert("info_tb", null, values);

        //用于将id添加到uri后面，然后返回给调用者
        return ContentUris.withAppendedId(uri, id);
    }

    //ContentProvider创建时调用
    SQLiteDatabase sqLiteDatabase;
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(getContext(),"stu.db",null,1) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("create table info_tb(_id integer primary key autoincrement," +
                        "name varchar(20))");
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            }
        };
        //执行read才会创建数据库
        sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();

        //参数Code：代表无法匹配
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //content://com.example.myprovider/helloworld
        //helloworld为处理路径，code为处理此路径时回传的值
        uriMatcher.addURI("com.example.myprovider","helloworld",1000);
        //helloworld下的所有数字
        uriMatcher.addURI("com.example.myprovider","helloworld/#",1001);
        //helloworld下的所有字符
        uriMatcher.addURI("com.example.myprovider","helloworld/*",1002);

        //返回true
        return true;
    }

    /***
     *
     * @param uri 资源
     * @param projection 查询的列
     * @param selection 查询条件
     * @param selectionArgs 查询条件值
     * @param sortOrder 排序
     * @return 游标
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        LogUtils.i(TAG, "query--");
        Cursor cursor = sqLiteDatabase.query("info_tb", projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        LogUtils.i(TAG, "update--uri "+ uri);
        int update = sqLiteDatabase.update("info_tb", values, selection, selectionArgs);
        return update;
    }
}