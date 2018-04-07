package data;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class GlideImageLoader {

    public static void imageLoadRequest(ImageView imageView, String imageUrl, int defaultImageResource){
        if(imageView != null && imageUrl != null){
            getImageFromGlide(imageView, imageUrl, defaultImageResource);
        }
    }

    private static void getImageFromGlide(ImageView imageView, String url, int defaultImageResource){
        RequestBuilder<Drawable> requestBuilder = Glide.with(imageView.getContext())
                .load(url);

        requestBuilder
                .thumbnail(Glide.with(imageView.getContext())
                        .load(url))
                .into(imageView);

        RequestOptions requestOptions=new RequestOptions()
                .placeholder(defaultImageResource)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerInside();



        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
