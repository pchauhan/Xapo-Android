package com.vnerds.xapo.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vnerds.xapo.R;
import com.vnerds.xapo.WebViewActivity;
import com.vnerds.xapo.objects.Git_Object;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<Git_Object.ItemsBean> mDataset;

        public  class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView mTextView,mTextDesc,mOwnerName;
            public ImageView mImgAvatar;
            public MyViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.tvTitle);
                mOwnerName = (TextView) v.findViewById(R.id.tvOwner);
                mTextDesc = (TextView) v.findViewById(R.id.tvDesc);
                mImgAvatar = (ImageView) v.findViewById(R.id.imgAvatar);
            }
        }


        public MyAdapter(ArrayList<Git_Object.ItemsBean> myDataset) {
            mDataset = myDataset;
        }

        public void updateAdapter(List<Git_Object.ItemsBean> myDataset) {
            mDataset = myDataset;
            notifyDataSetChanged();
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_recyclerview_listitem, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;

        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final Git_Object.ItemsBean itemsBeans = mDataset.get(position);
            holder.mTextView.setText(itemsBeans.getName());
            holder.mTextDesc.setText(itemsBeans.getDescription());

            holder.mOwnerName.setText(itemsBeans.getOwner().getLogin());
            Glide.with(holder.mTextView.getContext()).load(itemsBeans.getOwner().getAvatar_url()).into(holder.mImgAvatar);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),WebViewActivity.class);
                    intent.putExtra("title",itemsBeans.getName());
                    intent.putExtra("url",itemsBeans.getHtml_url());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }