package interactor;

import data.api.client.SpotifyService;
import data.model.AlbumResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;

/**
 * Created by sumon.chatterjee on 05/04/18.
 */

public class TrackInteractor {

    private SpotifyService spotifyService;

    public TrackInteractor(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public Observable<TrackResponse> getAlbumTracks(String albumId) {
        return spotifyService.getAlbumTracks(albumId);
    }

    public Observable<TrackResponse> searchTracks(String albumId) {
        return spotifyService.searchTracks(albumId);
    }
}
