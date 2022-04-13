package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.activity.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Bean.ListBean;
import Interface.OnItemClickListener;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<String> pdata = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private MyViewHolder myViewHolder;
    //创建单机回调接口
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MyRecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void setPdata(List<String> pdata){
        this.pdata = pdata;
        notifyDataSetChanged();
    }

    private int getRandomHeight() {
        return (int) (Math.random()*1000);
    }

    //返回一个自定义的ViewHolder，创建ViewHolder并返回，后续item布局里的控件都是从ViewHolder里面取出
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //填充布局，获取列表项布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //填充onCreateViewHolder方法返回的holder中的控件，通过方法提供ViewHolder，将数据绑定到ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //获取数据
        holder.textView.setText(pdata.get(position));
        //瀑布流处理
        if(recyclerView.getLayoutManager().getClass() == StaggeredGridLayoutManager.class) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getRandomHeight());
            holder.textView.setLayoutParams(params);
        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.textView.setLayoutParams(params);
        }

        View view = ((LinearLayout)holder.itemView).getChildAt(0);
        if(onItemClickListener != null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
    }

    //返回数据个数
    @Override
    public int getItemCount() {
        return pdata.size();
    }


    //这是RecyclerView.ViewHolder的实现类，可用于初始化item布局中的子控件。
    //需要注意的是，在这个类的构造方法中需要传递item布局的view给父类
     class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //获取对应列表项
            textView = itemView.findViewById(R.id.item_tv2);
        }
    }



}
