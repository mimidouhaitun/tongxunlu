package com.example.tarena.bmobdemo.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tarena.bmobdemo.R;
import com.example.tarena.bmobdemo.app.MyApp;
import com.example.tarena.bmobdemo.bean.ContactInfo;
import com.example.tarena.bmobdemo.ui.EditActivity;
import com.example.tarena.bmobdemo.util.ConstantData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoAdapter extends RecyclerView.Adapter<ContactInfoAdapter.MyViewHolder> implements View.OnClickListener {

    private List<ContactInfo> datas=new ArrayList<>();

    public ContactInfoAdapter(){
        super();
    }
    public ContactInfoAdapter(List<ContactInfo> list){
        this.datas=list;
    }
    public void setDatas(List<ContactInfo> listDatas) {
        this.datas = listDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyApp.CONTEXT).
                inflate(R.layout.rcyview_cardview_item, parent, false);

        view.setOnClickListener(this);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContactInfo info = datas.get(position);
        holder.tvName.setText(info.getName());
        holder.tvGender.setText(info.getGender());
        holder.tvAge.setText(info.getAge() + "");
        holder.tvPostName.setText(info.getPostName());
        holder.tvCompanyName.setText(info.getCompanyName());
        holder.tvMarry.setText(info.getMarry());
        holder.tvPone.setText(info.getMobile());
        holder.tvObjectID.setText(info.getObjectId()+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        TextView tvObjectID=v.findViewById(R.id.tvObjectID);
        String objectID=tvObjectID.getText().toString();
        Intent intent=new Intent(MyApp.CONTEXT,EditActivity.class);
        intent.putExtra(ConstantData.objectIdKey,objectID);
        MyApp.CONTEXT.startActivity(intent);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvGender;
        public TextView tvAge;
        public TextView tvMarry;
        public TextView tvPone;
        public TextView tvCompanyName;
        public TextView tvPostName;
        public TextView tvObjectID;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvMarry = itemView.findViewById(R.id.tvMarry);
            tvCompanyName = itemView.findViewById(R.id.tvCompanyName);
            tvPostName = itemView.findViewById(R.id.tvPostName);
            tvPone=itemView.findViewById(R.id.tvPhone);
            tvObjectID=itemView.findViewById(R.id.tvObjectID);
        }
    }
}
