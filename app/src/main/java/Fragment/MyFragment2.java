package Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.activity.R;

import Utils.LogUtils;

public class MyFragment2 extends Fragment {

    private static final String TAG = "MyFragment2";
    private View root;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.i(TAG, "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(TAG, "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(TAG, "onCreateView()");
        if(root == null){
            root = inflater.inflate(R.layout.fragment_my2, container, false);
        }
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(TAG, "onDestroyView()");
        if (root != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i(TAG, "onDetach()");
    }
}
