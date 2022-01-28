package Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyFragmentPagerAdapter;
import Adapter.ViewPagerAdapter;
import Bean.ListBean;
import Fragment.MyFragment;
import Fragment.MyFragment2;


public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    //Fragment的生命周期
    //onAttach()------------Fragment和Activity相关联时调用。可以通过该方法获取Activity引用，还可以通过getArguments()获取参数。
    //onCreate()------------Fragment被创建时调用
    //onCreateView()--------当Activity完成onCreate()时调用
    //onActivityCreated()---当Fragment所在的Activity启动完成后回调
    //onStart()-------------当Fragment可见时调用。
    //onResume()------------当Fragment可见且可交互时调用
    //onPause()-------------当Fragment不可交互但可见时调用。
    //onStop()--------------当Fragment不可见时调用。
    //onDestroyView()-------当Fragment的UI从视图结构中移除时调用。
    //onDestroy()-----------当Fragment销毁时调用。
    //onDetach()------------当Fragment和Activity解除关联时调用。

    //静态加载
    //定义Fragment的xml布局文件
    //自定义Fragment类，继承Fragment类或其子类，同时实现onCreate()方法，在方法中，通过inflater.inflate加载布局文件，接着返回其View
    //在需要加载Fragment的Activity对应布局文件中<fragment>的name属性设为全限定类名，即包名.fragment
    //最后在Activity调用setContentView()加载布局文件即可

    //动态加载
    //获得FragmentManager对象，通过getSupportFragmentManager()
    //获得FragmentTransaction对象，通过fm.beginTransaction()
    //调用add()方法或者replace()方法加载Fragment
    //最后调用commit()方法提交事务

    private Button button;
    private boolean flag = true;
    private ViewPager2 viewpager1, viewpager2;

    private LinearLayout ll1, ll2, ll3;
    private ImageView image1, image2, image3, image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        button = findViewById(R.id.btn);
        button.setOnClickListener(this);
        replaceFragment(new MyFragment());

        viewpager1 = (ViewPager2)findViewById(R.id.viewpager1);
        //添加数据
        String[] data = getResources().getStringArray(R.array.arr);
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list1.add(data[i]);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(list1);
        viewpager1.setAdapter(viewPagerAdapter);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);

        image1.setSelected(true);
        image = image1;
        viewpager2 = (ViewPager2)findViewById(R.id.viewpager2);
        List<Fragment> list2 = new ArrayList<>();
        list2.add(new MyFragment());
        list2.add(new MyFragment2());
        list2.add(new MyFragment());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),list2);
        viewpager2.setAdapter(adapter);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changePager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    private void changePager(int position) {
        image.setSelected(false);
        switch (position) {
            case R.id.ll1:
                viewpager2.setCurrentItem(0);
            case 0:
                image1.setSelected(true);
                image = image1;
                break;
            case R.id.ll2:
                viewpager2.setCurrentItem(1);
            case 1:
                image2.setSelected(true);
                image = image2;
                break;
            case R.id.ll3:
                viewpager2.setCurrentItem(2);
            case 2:
                image3.setSelected(true);
                image = image3;
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("msg", "fragment");
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_fragment,fragment);
        //将fragment添加到栈中，返回时会依次移除前面的fragment
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if(v != null){
            switch (v.getId()) {
                case R.id.btn:
                    if(flag){
                        replaceFragment(new MyFragment2());
                    }else {
                        replaceFragment(new MyFragment());
                    }
                    flag = !flag;
                    break;
                case R.id.ll1:
                case R.id.ll2:
                case R.id.ll3:
                    changePager(v.getId());
                    break;
            }
        }
    }
}
