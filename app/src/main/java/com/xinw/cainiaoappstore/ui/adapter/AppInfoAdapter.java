package com.xinw.cainiaoappstore.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinw.cainiaoappstore.R;
import com.xinw.cainiaoappstore.bean.AppInfo;
import com.xinw.cainiaoappstore.common.imageloader.ImageLoadConfig;
import com.xinw.cainiaoappstore.common.imageloader.ImageLoader;

/**
 * byD9ing on 2017/8/18.
 * Describe: App信息列表适配器
 * good luck
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, BaseViewHolder> {
    private ImageLoadConfig.Builder mBuilder;
    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public AppInfoAdapter() {
        super(R.layout.template_appinfo);

    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        ImageLoader.load(baseImgUrl + item.getIcon(), (ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name, item.getDisplayName())
                .setText(R.id.txt_brief, item.getBriefShow());

    }
}
