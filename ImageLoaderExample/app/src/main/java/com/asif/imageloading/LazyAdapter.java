package com.asif.imageloading;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class LazyAdapter extends RecyclerView.Adapter<LazyAdapter.ViewHolderLazy> {
    private Activity mActivity;
    private String[] mData;
    private static LayoutInflater mLayoutInflater = null;

    public LazyAdapter(Activity mActivity, String[] mData) {
        this.mActivity = mActivity;
        this.mData = mData;
        mLayoutInflater = mActivity.getLayoutInflater();
    }

    @NonNull
    @Override
    public ViewHolderLazy onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolderLazy(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLazy viewHolderLazy, int i) {
        viewHolderLazy.textView.setText("item " + i);
        Glide.with(mActivity)
                .load(mData[i])
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading)
                .into(viewHolderLazy.imageView);
        /*ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(false)
                .showImageForEmptyUri(R.drawable.ic_launcher_background)
                .showImageOnFail(R.drawable.loading)
                .showImageOnLoading(R.drawable.loading).build();
        imageLoader.displayImage(mData[i], viewHolderLazy.imageView, options);*/
    }


    @Override
    public int getItemCount() {
        return mData.length;
    }

    class ViewHolderLazy extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolderLazy(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
