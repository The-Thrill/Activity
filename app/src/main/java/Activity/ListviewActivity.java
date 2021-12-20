package Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.MyArrayAdapter;
import Bean.ListBean;

public class ListviewActivity extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener, AbsListView.OnScrollListener{

    private static final String TAG = "ListviewActivity";
    private ListView listView;
    private List<ListBean> listBeans;
    private MyArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //1、获取ListView
        listView = (ListView)findViewById(R.id.listview);
        //2、创建适配器对象
        String[] data = getResources().getStringArray(R.array.arr);//模拟网络数组数据
        listBeans = new ArrayList<ListBean>();
        for (int i = 0; i < data.length; i++) {
            ListBean listBean = new ListBean();
            listBean.setTitle(data[i]);
            listBeans.add(listBean);
        }
        adapter = new MyArrayAdapter(ListviewActivity.this, listBeans);
        //3、加载适配器
        listView.setAdapter(adapter);
        //4、设置监听
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnScrollListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListviewActivity.this,"点击了"+listBeans.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        Log.e(TAG, "点击了: ----------------"+listBeans.get(position).getTitle());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListviewActivity.this,"长按删除"+listBeans.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        listBeans.remove(position);
        adapter.notifyDataSetChanged();
        Log.e(TAG, "长按删除: ----------------"+listBeans.get(position).getTitle());
        return true; //返回false会和点击监听冲突，只监听长按设置true
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING) {  //滑动
            Toast.makeText(ListviewActivity.this,"滑动",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "滑动: ----------------");
        }else if (scrollState == SCROLL_STATE_IDLE) {   //停止
            Toast.makeText(ListviewActivity.this,"停止",Toast.LENGTH_SHORT).show();
            //模拟添加数据
            ListBean listBean = new ListBean();
            listBean.setTitle("nihao");
            listBeans.add(listBean);
            adapter.notifyDataSetChanged();
            Log.e(TAG, "停止: ----------------");

        }else if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {   //手指离开
            Toast.makeText(ListviewActivity.this,"手指离开",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "手指离开: ----------------");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


}
