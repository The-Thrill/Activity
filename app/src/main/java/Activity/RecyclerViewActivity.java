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

    //RecyclerView相对于ListView的优点
    //1、可以使用布局管理器LayoutManager来管理RecyclerView的显示方式，水平、垂直、网络、网格交错
    //2、自定义item的分割条
    //3、可以控制item的添加和删除动画，可以自定义动画，配合具体场景
    //4、可以动态的在指定位置添加和删除某一项，而列表不会回到顶部，动态的update列表数据
    //5、缺点就是没有OnItemClickListener(),需要自己在RecyclerView内部自定义列表项的点击事件或者长按事件
    //6、在Material Design中和CardView配合使用，显示效果非常突出

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
