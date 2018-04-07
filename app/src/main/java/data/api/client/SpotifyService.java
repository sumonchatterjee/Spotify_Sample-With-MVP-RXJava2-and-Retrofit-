package data.api.client;

import java.util.List;

import data.model.AlbumResponse;
import data.model.AudioFeaturesResponse;
import data.model.ProfileResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public interface SpotifyService {

    Observable<AlbumResponse> getSavedAlbum();

    Observable<ProfileResponse> getUserProfile();

    Observable<TrackResponse> getAlbumTracks(String albumId);

    Observable<TrackResponse> searchTracks(String albumId);

    Observable<AudioFeaturesResponse> getAudioFeaturesForTrack(String trackId);
}
