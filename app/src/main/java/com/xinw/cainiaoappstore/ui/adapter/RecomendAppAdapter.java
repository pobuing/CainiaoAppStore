package com.xinw.cainiaoappstore.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.http.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * byD9ing on 2017/8/10.
 * Describe: 推荐页面的列表适配器
 * good luck
 */

public class RecomendAppAdapter extends RecyclerView.Adapter<RecomendAppAdapter.ReViewHolder> {

    private final LayoutInflater mLayoutInflater;

    private Context mContext;
    private List<AppInfo> mDatas;

    public RecomendAppAdapter(Context context, List<AppInfo> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ReViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.template_recomend_app, parent, false);
        ReViewHolder reViewHolder = new ReViewHolder(view);
        return reViewHolder;
    }

    @Override
    public void onBindViewHolder(ReViewHolder holder, int position) {
        AppInfo appInfo = mDatas.get(position);
        // TODO: loadImage
        Picasso.with(mContext).load(ApiService.BASE_IMG_URL+appInfo.getIcon()).into(holder.imgIcon);
        holder.textSize.setText((appInfo.getApkSize()/1024/1024)+" MB");
        holder.textTitle.setText(appInfo.getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ReViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_size)
        TextView textSize;
        @BindView(R.id.btn_dl)
        Button btnDl;

        public ReViewHolder(View itemView) {
            super(itemView);
            // TODO: 绑定
            ButterKnife.bind(this, itemView);
        }
    }
}
