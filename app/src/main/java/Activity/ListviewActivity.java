package Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyArrayAdapter;
import Bean.ListBean;
import Utils.LogUtils;

public class ListviewActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener, AbsListView.OnScrollListener{

    private static final String TAG = "ListviewActivity";
    private ListView listView;
    private List<ListBean> listBeans;
    private MyArrayAdapter adapter;
    private Button top,flash,down,add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        //1、获取ListView
        listView = (ListView)findViewById(R.id.listview);
        top = (Button)findViewById(R.id.top);
        flash = (Button)findViewById(R.id.flash);
        down = (Button)findViewById(R.id.down);
        add = (Button)findViewById(R.id.add);
        //动态获取表头和表尾
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View header = layoutInflater.inflate(R.layout.view_header,null);
        View footer = layoutInflater.inflate(R.layout.view_footer,null);
        //2、创建适配器对象
        String[] data = getResources().getStringArray(R.array.arr);//模拟网络数组数据
        listBeans = new ArrayList<ListBean>();
        for (int i = 0; i < data.length; i++) {
            ListBean listBean = new ListBean();
            listBean.setTitle(data[i]);
            listBeans.add(listBean);
        }
        adapter = new MyArrayAdapter(ListviewActivity.this, listBeans);
        //添加表头和表尾
        listView.addHeaderView(header);
        listView.addFooterView(footer);
        //设置列表从底部开始
        listView.setStackFromBottom(true);
        //设置分割线
        listView.setDivider(new ColorDrawable(Color.GRAY));
        listView.setDividerHeight(1);
        //隐藏滑动条（android:scrollbars="none"）
        listView.setVerticalScrollBarEnabled(false);
        //3、加载适配器
        listView.setAdapter(adapter);
        //4、设置监听
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnScrollListener(this);

        top.setOnClickListener(this);
        flash.setOnClickListener(this);
        down.setOnClickListener(this);
        add.setOnClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListviewActivity.this,"点击了"+listBeans.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        LogUtils.i(TAG, "点击了: ----------------"+listBeans.get(position).getTitle());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListviewActivity.this,"长按删除"+listBeans.get(position).getTitle(),Toast.LENGTH_SHORT).show();
        listBeans.remove(position);
        adapter.notifyDataSetChanged();
        LogUtils.i(TAG, "长按删除: ----------------"+listBeans.get(position).getTitle());
        return true; //返回false会和点击监听冲突，只监听长按设置true
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_FLING) {  //惯性滑动，用力快速滑动时可监听到此值
            LogUtils.i(TAG, "SCROLL_STATE_FLING: ----------------惯性滑动");
        }else if (scrollState == SCROLL_STATE_IDLE) {   //停止，不滚动时的状态，通常会在滚动停止时监听到此状态
            LogUtils.i(TAG, "SCROLL_STATE_IDLE: ----------------停止");
            // 判断滚动到底部
            if (listView.getLastVisiblePosition() == (listView.getCount() - 1)) {
                LogUtils.i(TAG, "SCROLL_STATE_IDLE: ----------------底部");
            }

            // 判断滚动到顶部
            if(listView.getFirstVisiblePosition() == 0){
                LogUtils.i(TAG, "SCROLL_STATE_IDLE: ----------------顶部");
            }

        }else if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {   //滑动，正在滚动的状态
            LogUtils.i(TAG, "SCROLL_STATE_TOUCH_SCROLL: ----------------滑动");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        LogUtils.i(TAG,firstVisibleItem+"   firstVisibleItem");//第一条可见条目
        LogUtils.i(TAG,visibleItemCount+"   visibleItemCount");//当前可见条目
        LogUtils.i(TAG,visibleItemCount+"   totalItemCount");//总体条目
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top:
                listView.setSelection(0);
                adapter.notifyDataSetInvalidated(); //清空所有信息，重新布局
                break;
            case R.id.flash:
                adapter.notifyDataSetChanged(); //保存刷新前位置
                break;
            case R.id.down:
                listView.setSelection(listView.getCount()-1);
                adapter.notifyDataSetInvalidated(); //清空所有信息，重新布局
                break;
            case R.id.add:
                ListBean listBean = new ListBean();
                listBean.setTitle(""+listView.getCount());
                listBeans.add(listBean);
                adapter.notifyDataSetChanged(); //保存刷新前位置
                break;

        }

    }
}
