package com.animationdemo.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.animationdemo.R;
import com.animationdemo.base.App;
import com.animationdemo.bean.BannerBean;
import com.animationdemo.widget.FillParentVideoView;
import com.bumptech.glide.Glide;
import com.danikula.videocache.HttpProxyCacheServer;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoBannerAdapter extends BannerAdapter<BannerBean, RecyclerView.ViewHolder> {
    private Context context;
    private Banner banner;

    public VideoBannerAdapter(Context context, List<BannerBean> datas, Banner banner) {
        super(datas);
        this.context = context;
        this.banner = banner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
//        ImageView imageView = (ImageView)  BannerUtils.getView(parent, R.layout.item_banner_image);
//        //通过裁剪实现圆角
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            BannerUtils.setBannerRound(imageView,20);
//        }
        switch (viewType) {
            case 1:
                ImageView imageView = (ImageView)  BannerUtils.getView(parent, R.layout.item_banner_image);
                //通过裁剪实现圆角
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    BannerUtils.setBannerRound(imageView,20);
                }
                return new ImageHolder(imageView);
            case 2:
                View view = BannerUtils.getView(parent, R.layout.item_banner_video);
                //通过裁剪实现圆角
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    BannerUtils.setBannerRound(view,20);
                }
                return new VideoHolder(view);
        }
        ImageView imageView = (ImageView)  BannerUtils.getView(parent, R.layout.item_banner_image);
        //通过裁剪实现圆角
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BannerUtils.setBannerRound(imageView,20);
        }
        return new ImageHolder(imageView);
    }

    @Override
    public int getItemViewType(int position) {
        return getData(getRealPosition(position)).viewType;
    }

    @Override
    public void onBindView(RecyclerView.ViewHolder holder, BannerBean data, int position, int size) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case 1:
                ImageHolder imageHolder = (ImageHolder)holder;
                Glide.with(holder.itemView)
                        .load(data.imageUrl)
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into(imageHolder.imageView);

                break;
            case 2:
                final VideoHolder videoHolder = (VideoHolder)holder;
                HttpProxyCacheServer proxy = App.getProxy(context);
                String proxyUrl = proxy.getProxyUrl(data.imageUrl);
                final FillParentVideoView player = videoHolder.player;
                player.setVideoPath(proxyUrl);

                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                        Log.e("------------->", "start");
                    }
                });
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.e("------------->", "Completion111");
                        banner.isAutoLoop(true);
                        banner.start();
                    }
                });
                break;
        }
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        public FillParentVideoView player;

        public VideoHolder(@NonNull View view) {
            super(view);
            player = view.findViewById(R.id.player);
        }
    }

}
