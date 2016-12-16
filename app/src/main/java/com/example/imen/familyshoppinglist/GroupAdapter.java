package com.example.imen.familyshoppinglist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imen on 12/12/2016.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder>{

private List<String> mList;

public class MyViewHolder extends RecyclerView.ViewHolder  {
    TextView tv1, tv2;
    ImageView imageView;

    public MyViewHolder(View itemView) {
        super(itemView);

        tv1 = (TextView) itemView.findViewById(R.id.list_title);
        tv2 = (TextView) itemView.findViewById(R.id.list_desc);
        imageView = (ImageView) itemView.findViewById(R.id.list_avatar);

    }
}

    /*public GroupAdapter(List<String> mList) {
        this.mList = mList;
    }*/
    ArrayList<String> name ;
    Context context;
    LayoutInflater inflater;

    public GroupAdapter(Context context, ArrayList<String> name) {
        this.context = context;
        this.name=name;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.my_text_view, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(name.get(position));
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MyViewHolder vholder = (MyViewHolder) v.getTag();
            int position = vholder.getPosition();

            Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public int getItemCount() {
        return name.size();
    }}