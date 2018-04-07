
package interactor;


import data.api.client.SpotifyService;
import data.model.AlbumResponse;
import io.reactivex.Observable;

public class AlbumInteractor {

  private SpotifyService spotifyService;

  public AlbumInteractor(SpotifyService spotifyService) {
    this.spotifyService = spotifyService;
  }

  public Observable<AlbumResponse> getAlbums() {
    return spotifyService.getSavedAlbum();
  }

}
