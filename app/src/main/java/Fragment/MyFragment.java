package Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.activity.R;

import Utils.LogUtils;

public class MyFragment extends Fragment {

    //打开界面
    //onAttach()->onCreate()->onCreateView()->onActivityCreated()->onStart()->onResume()
    //按下主屏键
    //onPause()->onStop()
    //重新打开界面
    //onStart()->onResume()
    //按下后退键
    //onPause()->onStop()->onDestroyView()->onDestroy()->onDetach()

    private static final String TAG = "MyFragment";
    private View root;
    private static String s = "";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.i(TAG, "------onAttach()");
    }

    //一般进行bundle解析
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            s = getArguments().getString("msg");
        }
        LogUtils.i(TAG, "------onCreate()"+s);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, "------onCreateView()");
        if(root == null){
            root = inflater.inflate(R.layout.fragment_my, container, false);
        }
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i(TAG, "------onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, "------onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG, "------onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(TAG, "------onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(TAG, "------onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(TAG, "------onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "------onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i(TAG, "------onDetach()");
    }
}