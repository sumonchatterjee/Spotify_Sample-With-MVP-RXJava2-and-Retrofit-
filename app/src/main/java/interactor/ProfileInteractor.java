package interactor;

import data.api.client.SpotifyService;
import data.model.AlbumResponse;
import data.model.ProfileResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;

/**
 * Created by sumon.chatterjee on 04/04/18.
 */

public class ProfileInteractor {

    private SpotifyService spotifyService;

    public ProfileInteractor(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }


    public Observable<ProfileResponse> getUserProfile() {
        return spotifyService.getUserProfile();
    }


}
