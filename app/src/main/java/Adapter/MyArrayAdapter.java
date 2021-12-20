package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import Activity.ListViewHolder;
import Bean.ListBean;

import com.example.activity.R;

import java.util.List;

public class MyArrayAdapter extends BaseAdapter {

    private final Context context;
    private final List<ListBean> listBean;
    private int mSelect;   //选中项

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
            listViewHolder = new ListViewHolder(context, convertView);
            //将ViewHolder与convertView绑定，下次使用时无须再次实例化
            convertView.setTag(listViewHolder);
        } else {
            //在convertView不为空时，直接获取绑定的ViewHolder
            listViewHolder = (ListViewHolder) convertView.getTag();
        }
        //装载对应的数据源
        listViewHolder.setBean(listBean.get(position));

        return convertView;
    }

}
