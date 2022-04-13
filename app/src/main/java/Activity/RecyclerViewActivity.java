package Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerViewAdapter;
import Bean.ListBean;
import Interface.OnItemClickListener;
import Utils.LogUtils;

public class RecyclerViewActivity extends Activity implements View.OnClickListener{

    //RecyclerView相对于ListView的优点
    //1、可以使用布局管理器LayoutManager来管理RecyclerView的显示方式，水平、垂直、网络、网格交错
    //2、自定义item的分割条
    //3、可以控制item的添加和删除动画，可以自定义动画，配合具体场景
    //4、可以动态的在指定位置添加和删除某一项，而列表不会回到顶部，动态的update列表数据
    //5、缺点就是没有OnItemClickListener(),需要自己在RecyclerView内部自定义列表项的点击事件或者长按事件
    //6、在Material Design中和CardView配合使用，显示效果非常突出

    private static final String TAG = "RecyclerViewActivity";
    private Button bt1, bt2;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initView();
        addListener();

        //1、初始化控件
        recyclerView = findViewById(R.id.recyclerview);
        //2、设置RecyclerView布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //3、横行排列
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //4、反向排列
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, recyclerView);
        //4、设置动画，默认动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //5、设置适配器
        recyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.i(TAG, "点击了: ----------------"+position);
            }
        });

    }

    private void initView() {
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
    }

    private void addListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    data.add("第"+ i +"条数据");
                }
                myRecyclerViewAdapter.setPdata(data);
                break;
            case R.id.bt2:
                if(recyclerView.getLayoutManager().getClass() == LinearLayoutManager.class) {
                    //切换为网格布局
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }else if(recyclerView.getLayoutManager().getClass() == GridLayoutManager.class) {
                    //切换为瀑布流布局
                    //多少列 排列方向
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(staggeredGridLayoutManager);
                }else {
                    //切换为线性布局
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
                break;
        }
    }
}
