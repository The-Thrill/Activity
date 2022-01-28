package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import Bean.ListBean;
import Utils.KeyboardUtils;
import Utils.LogUtils;

import com.example.activity.R;

import java.util.List;

public class MyArrayAdapter extends BaseAdapter {

    private static final String TAG = "MyArrayAdapter";
    private final Context context;
    private final List<ListBean> listBean;

    public MyArrayAdapter(Context context, List<ListBean> mList) {
        this.context = context;
        this.listBean = mList;

    }

    //数据长度
    @Override
    public int getCount() {
        return listBean.size();
    }

    @Override
    public Object getItem(int position) {
        return listBean.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获得ListViewActivity 的 LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(context);
        //声明一个ViewHolder
        ListViewHolder listViewHolder;
        if (convertView == null) {
            //convertView为null时，重新获取一遍资源
            convertView = inflater.inflate(R.layout.item_listiew, null);
            //实例化一次ViewHolder
            listViewHolder = new ListViewHolder();
            listViewHolder.textView = (TextView) convertView.findViewById(R.id.item_tv);
            listViewHolder.button = (Button) convertView.findViewById(R.id.bt);
            listViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
            listViewHolder.editText = (EditText) convertView.findViewById(R.id.et);
            //将ViewHolder与convertView绑定，下次使用时无须再次实例化
            convertView.setTag(listViewHolder);
        } else {
            //在convertView不为空时，直接获取绑定的ViewHolder
            listViewHolder = (ListViewHolder) convertView.getTag();
        }
        //装载对应的数据源
        listViewHolder.textView.setText(listBean.get(position).getTitle());
        addListener(listViewHolder,position);
        return convertView;
    }

    static class ListViewHolder{
        TextView textView;
        Button button;
        CheckBox checkBox;
        EditText editText;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void addListener(ListViewHolder listViewHolder, int i) {
        listViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(TAG,listBean.get(i).getTitle()+"被点击");
            }
        });

        listViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listViewHolder.checkBox.isChecked()){
                    LogUtils.i(TAG,listBean.get(i).getTitle()+"被选中");
                }else {
                    LogUtils.i(TAG,listBean.get(i).getTitle()+"被取消");
                }
            }
        });

        listViewHolder.editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtils.i(TAG,"弹出键盘");
                return false;
            }
        });
    }

}
