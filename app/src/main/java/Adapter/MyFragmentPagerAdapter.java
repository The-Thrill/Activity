package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentStateAdapter {

    //1）FragmentPagerAdapter,使用FragmentPagerAdapter 时，Fragment对象会一直存留在内存中，
    // 所以当有大量的显示页时，就不适合用FragmentPagerAdapter了，FragmentPagerAdapter 适用于只有少数的page情况，像选项卡。
    //原因：步长外的页面会调用destroyItem，但只有onDestroyView调用了，没有调用onDestroy，也没有调用onDetach，
    // 所以fragment只是把上面的view销毁了，fragment并没有销毁，下次再创建的时候，只会调用onCreateView和onActivityCreated

    //2）FragmentStateAdapter
    //这个时候你可以考虑使用FragmentStateAdapter ，当使用FragmentStateAdapter 时，
    // 如果Fragment不显示，那么Fragment对象会被销毁，（滑过后会保存当前界面，以及下一个界面和上一个界面（如果有），最多保存3个，其他会被销毁掉）
    //原因：会真正销毁（同时销毁view和fragment，调用onDestroyView以及其后面的所有销毁方法），重建时会从最初的onAttach开始一直到onActivityCreated。
    // 但在回调onDestroy()方法之前会回调onSaveInstanceState(Bundle outState)方法来保存Fragment的状态，
    // 下次Fragment显示时通过onCreate(Bundle savedInstanceState)把存储的状态值取出来，FragmentStatePagerAdapter 比较适合页面比较多的情况，像一个页面的ListView

    private List<Fragment> list;

    public MyFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> list) {
        super(fragmentManager, lifecycle);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
