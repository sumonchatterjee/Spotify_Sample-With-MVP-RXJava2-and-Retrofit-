package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.List;

import adapters.TracksAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import data.api.client.SpotifyClient;
import data.model.Items;
import data.model.TrackResponse;
import interactor.TrackInteractor;
import presenter.TrackPresenter;
import spotify.com.spotifysample.R;


public class TrackFragment extends BaseFragment implements TrackPresenter.View,TracksAdapter.onItemClickListener {

    private TrackPresenter tracksPresenter;
    private TracksAdapter mAdapter;
    private String albumId,albumImage;

    @BindView(R.id.track_rv) RecyclerView trackRv;
    @BindView(R.id.loaders) ProgressBar loaders;
    @BindView(R.id.search_txt) EditText searchTrack;


    private OnFragmentInteractionListener mListener;

    public TrackFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mListener =  (OnFragmentInteractionListener) context;
    }


    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracksPresenter = new TrackPresenter(new TrackInteractor(new SpotifyClient()), getContext());
        tracksPresenter.setView(this);
        mAdapter = new TracksAdapter(getContext());
        mAdapter.setmItemClickListener(this);


        // Get album id
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            albumId = bundle.getString("ALBUM_ID");
            albumImage = bundle.getString("ALBUM_IMAGE");
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tracks_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tracksPresenter.getTrackForAlbums(albumId);
        searchTrack.addTextChangedListener(watch);

    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        trackRv.setLayoutManager(mLayoutManager);
        trackRv.setAdapter(mAdapter);

    }


    @Override
    public Context context() {
        return getContext();
    }


    @Override
    public void displayTracksForAlbum(TrackResponse tracks) {
        if (tracks != null) {
            List<Items> trackItems = tracks.getItems();
            if (trackItems!=null && trackItems.size() > 0) {
                mAdapter.setTrackItems(trackItems);
                mAdapter.setAlbumImage(albumImage);
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void showLoader() {
        loaders.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loaders.setVisibility(View.GONE);
    }


    @Override
    public void onTrackItemClick(String trackId) {
        Bundle bundle = new Bundle();
        bundle.putString("TRACK_ID",trackId);
        DetailFragment trackFragment = new DetailFragment();
        trackFragment.setArguments(bundle);

        BaseFragment
                .addToBackStack(getFragmentManager(), trackFragment, false);
    }


    TextWatcher watch = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int a, int b, int c) {
            // TODO Auto-generated method stub

            if(s.length()>2){
                tracksPresenter.searchTracks(s.toString());
            }
        }};
}
