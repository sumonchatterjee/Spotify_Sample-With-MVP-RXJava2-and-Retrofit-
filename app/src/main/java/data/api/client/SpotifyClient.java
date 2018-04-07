package data.api.client;

import data.api.retrofits.SpotifyRetrofitClient;
import data.model.AlbumResponse;
import data.model.AudioFeaturesResponse;
import data.model.ProfileResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class SpotifyClient extends SpotifyRetrofitClient implements SpotifyService{
    @Override
    public Observable<AlbumResponse> getSavedAlbum() {
        return getSpotifyService().getAlbum()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<ProfileResponse> getUserProfile() {
        return getSpotifyService().getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<TrackResponse> getAlbumTracks(String albumId) {
        return getSpotifyService().getTracks(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<AudioFeaturesResponse> getAudioFeaturesForTrack(String trackId) {
        return getSpotifyService().getAudioFeaturesForTrack(trackId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<TrackResponse> searchTracks(String albumId) {
        return getSpotifyService().searchTrack(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
