package Activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.R;

import Bean.ListBean;

public class ListViewHolder {

    private LinearLayout layout;
    private TextView textView;

    private Context context;
    private View view;
    private ListBean bean;



    public ListViewHolder(Context context, View view) {
        this.context = context;
        this.view = view;
        init();
    }

    private void init() {
        layout = (LinearLayout) view.findViewById(R.id.layout);
        textView = (TextView) view.findViewById(R.id.item_tv);
    }

    /**
     * @param bean 要设置的 bean
     */
    public void setBean(ListBean bean) {
        this.bean = bean;
        deployData();
    }

    /**
     * 部署数据到对应的控件
     * @Title: deployData
     * @param
     * @return void
     */
    private void deployData() {
        textView.setText(bean.getTitle());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, bean.getTitle() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
