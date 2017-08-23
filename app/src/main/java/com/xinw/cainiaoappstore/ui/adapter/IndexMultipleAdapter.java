package com.xinw.cainiaoappstore.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.Banner;
import com.xinw.cainiaoappstore.bean.IndexBean;
import com.xinw.cainiaoappstore.common.imageloader.ImageLoader;
import com.xinw.cainiaoappstore.ui.widget.BannerLayout;
import com.xinw.cainiaoappstore.ui.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * byD9ing on 2017/8/18.
 * Describe:
 * good luck
 */

public class IndexMultipleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    public static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;
    private final LayoutInflater mLayoutInflater;
    private IndexBean mIndexBean;
    private Context mContext;

    public IndexMultipleAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }

        return 0;
    }

    public void setData(IndexBean indexBean) {

        mIndexBean = indexBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {

            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {

            return new NavIconViewHolder(mLayoutInflater.inflate(R.layout.template_nav_icon, parent, false));

        } else if (viewType == TYPE_APPS) {

            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {

            return new AppViewHolder(mLayoutInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_GAMES);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<Banner> banners = mIndexBean.getBanners();
            ArrayList<String> urls = new ArrayList<>(banners.size());
            for (Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);
            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Log.d(TAG, "onItemClick: " + position);
                }
            });
        } else if (position == 1) {
            NavIconViewHolder bannerViewHolder = (NavIconViewHolder) holder;

            bannerViewHolder.mLayoutHotApp.setOnClickListener(this);
            bannerViewHolder.mLayoutHotGame.setOnClickListener(this);
            bannerViewHolder.mLayoutHotSubject.setOnClickListener(this);
        } else {
            AppViewHolder appViewHolder = (AppViewHolder) holder;
            AppInfoAdapter appInfoAdapter = AppInfoAdapter.build().showPosition(false)
                    .showCategoryName(true)
                    .showShowBrief(false)
                    .build();
            if (appViewHolder.type == TYPE_APPS) {
                appViewHolder.mText.setText("热门应用");
                appInfoAdapter.addData(mIndexBean.getRecommendApps());
            } else {
                appViewHolder.mText.setText("热门游戏");
                appInfoAdapter.addData(mIndexBean.getRecommendGames());
            }

            // TODO: set inner adapter
            appViewHolder.mRecyclerView.setAdapter(appInfoAdapter);
            // TODO: set inner click
            appViewHolder.mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {

    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        BannerLayout mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mBanner.setImageLoader(new ImgLoader());

        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        public NavIconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        private final int type;
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public AppViewHolder(View itemView, int typeApps) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.type = typeApps;

            initRecyclerView();

        }

        private void initRecyclerView() {

            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext) {

                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });

            DividerItemDecoration itemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);

            mRecyclerView.addItemDecoration(itemDecoration);

        }
    }

    class ImgLoader implements BannerLayout.ImageLoader {


        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }
    }
}
