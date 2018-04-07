package interactor;

import data.api.client.SpotifyService;
import data.model.AlbumResponse;
import data.model.AudioFeaturesResponse;
import io.reactivex.Observable;

public class DetailInteractor {

    private SpotifyService spotifyService;

    public DetailInteractor(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public Observable<AudioFeaturesResponse> getAudioFeaturesForTrack(String trackId) {
        return spotifyService.getAudioFeaturesForTrack(trackId);
    }
}
