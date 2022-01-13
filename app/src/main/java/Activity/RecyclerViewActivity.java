package Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerViewAdapter;
import Bean.ListBean;
import Interface.OnItemClickListener;
import Utils.LogUtils;

public class RecyclerViewActivity extends Activity {

    private static final String TAG = "ListviewActivity";
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        //1、初始化控件
        recyclerView = findViewById(R.id.recyclerview);
        //2、设置RecyclerView布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false));
        //3、初始化数据适配器
        String[] data = getResources().getStringArray(R.array.arr);//模拟网络数组数据
        List<ListBean> listBeans = new ArrayList<ListBean>();
        for (int i = 0; i < data.length; i++) {
            ListBean listBean = new ListBean();
            listBean.setTitle(data[i]);
            listBeans.add(listBean);
        }
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(listBeans, RecyclerViewActivity.this);
        //4、设置动画，默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //5、设置适配器
        recyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.i(TAG, "点击了: ----------------"+listBeans.get(position).getTitle());
            }
        });

    }
}
