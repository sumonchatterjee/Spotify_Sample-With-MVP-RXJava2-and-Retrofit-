package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import data.GlideImageLoader;
import data.model.Items;
import spotify.com.spotifysample.R;


public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TrackViewHolder> implements View.OnClickListener {
    private List<Items> mTracks;
    private Context mContext;
    private String url;
    private onItemClickListener mItemClickListener;


    public TracksAdapter(Context context) {
        this.mContext = context;
    }

    public void setTrackItems(List<Items> trackDetails) {
        this.mTracks = trackDetails;
    }

    public void setAlbumImage(String url) {
        this.url = url;
    }


    public void setmItemClickListener(onItemClickListener listener){
        this.mItemClickListener = listener;
    }


    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_row, parent, false);
        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Items tracks = mTracks.get(position);
        if (tracks != null) {
            holder.trackName.setText(tracks.getName());
            holder.duration.setText(tracks.getDuration_ms() + " ms");
            holder.artist.setText(tracks.getArtists().get(0).getName());
            holder.mainContainer.setTag(tracks.getId());
            holder.mainContainer.setOnClickListener(this);

            GlideImageLoader.imageLoadRequest(holder.imgvw, url, R.drawable.ic_launcher_background);
        }

    }

    @Override
    public int getItemCount() {
        if (mTracks != null)
            return mTracks.size();
        else
            return 0;
    }


    @Override
    public void onClick(View view) {
        String trackId = (String)view.getTag();
        mItemClickListener.onTrackItemClick(trackId);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView trackName, duration, artist;
        private ImageView imgvw;
        private RelativeLayout mainContainer;


        public TrackViewHolder(View view) {
            super(view);
            trackName = view.findViewById(R.id.track_name);
            duration = view.findViewById(R.id.track_duration);
            artist = view.findViewById(R.id.track_artist);
            imgvw = view.findViewById(R.id.imgvw);
            mainContainer = view.findViewById(R.id.main_container);
        }
    }


    public interface onItemClickListener{
        void onTrackItemClick(String trackId);
    }

}
