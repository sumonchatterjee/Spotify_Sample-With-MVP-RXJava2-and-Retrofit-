package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import data.api.client.SpotifyClient;
import data.model.AudioFeaturesResponse;
import interactor.DetailInteractor;
import presenter.DetailPresenter;
import spotify.com.spotifysample.R;

public class DetailFragment extends BaseFragment implements DetailPresenter.View{
    private DetailPresenter detailPresenter;
    private String trackId;

    @BindView(R.id.danceability) TextView danceabilityTv;
    @BindView(R.id.energy) TextView energyTv;
    @BindView(R.id.key) TextView keyTv;
    @BindView(R.id.loudness) TextView loudnessTv;
    @BindView(R.id.speechiness) TextView speechinessTv;
    @BindView(R.id.acousticness) TextView acousticnessTv;
    @BindView(R.id.instrumentalness) TextView instrumentalnessTv;
    @BindView(R.id.loaders) ProgressBar loaders;



    public DetailFragment() {
       // setHasOptionsMenu(true);
    }

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mListener = (OnFragmentInteractionListener) context;
    }


    @Override
    public void onResume() {
        super.onResume();
        mListener.showDrawerToggle(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPresenter = new DetailPresenter(new DetailInteractor(new SpotifyClient()), getContext());
        detailPresenter.setView(this);


        // Get album id
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            trackId = bundle.getString("TRACK_ID");
            detailPresenter.getAudioFeaturesForTrack(trackId);

        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        handleNavigation();
    }

    @Override
    public void showLoader() {
        //loaders.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        //loaders.setVisibility(View.GONE);
    }

    @Override
    public void showAudioFeatures(AudioFeaturesResponse audioFeaturesResponse) {
      if(audioFeaturesResponse!=null){
          danceabilityTv.setText("Danceability "+String.valueOf(audioFeaturesResponse.getDanceability()));
          energyTv.setText("Energy "+String.valueOf(audioFeaturesResponse.getEnergy()));
          keyTv.setText(" Key"+String.valueOf(audioFeaturesResponse.getKey()));
          loudnessTv.setText("Loudness "+String.valueOf(audioFeaturesResponse.getLoudness()));
          speechinessTv.setText(" Speechiness"+String.valueOf(audioFeaturesResponse.getSpeechiness()));
          acousticnessTv.setText(String.valueOf("Acousticness " +audioFeaturesResponse.getAcousticness()));
          instrumentalnessTv.setText(String.valueOf(" Uri"+audioFeaturesResponse.getUri()));
      }

    }

    @Override
    public Context context() {
        return null;
    }

    //handle back navigation
    private void handleNavigation(){
        android.support.v7.widget.Toolbar bar=((MainActivity)this.getActivity()).getToolbar();
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popBackStack(getFragmentManager());
            }
        });
    }

}
