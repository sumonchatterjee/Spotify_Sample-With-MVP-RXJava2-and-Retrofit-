package data.api.client;

import data.model.AlbumResponse;
import data.model.AudioFeaturesResponse;
import data.model.ProfileResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;

public interface SpotifyService {

    Observable<AlbumResponse> getSavedAlbum();

    Observable<ProfileResponse> getUserProfile();

    Observable<TrackResponse> getAlbumTracks(String albumId);

    Observable<TrackResponse> searchTracks(String albumId);

    Observable<AudioFeaturesResponse> getAudioFeaturesForTrack(String trackId);
}
