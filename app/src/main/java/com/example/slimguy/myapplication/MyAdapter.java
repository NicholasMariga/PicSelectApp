package com.example.slimguy.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String data1[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String s1[], int img[]){

        context = ct;
        data1 = s1;
        images = img;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mpicNameView.setText(data1[position]);
        holder.mImageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mIdView;
        TextView mpicNameView;
        ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIdView = (TextView) itemView.findViewById(R.id.id_text);
            mpicNameView = (TextView) itemView.findViewById(R.id.content);
            mImageView = (ImageView) itemView.findViewById(R.id.pic);
        }
    }
}
