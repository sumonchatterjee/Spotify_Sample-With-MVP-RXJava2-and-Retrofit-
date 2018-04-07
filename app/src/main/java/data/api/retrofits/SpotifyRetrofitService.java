
package data.api.retrofits;

import java.util.List;

import data.api.Constant;
import data.model.AlbumResponse;
import data.model.AudioFeaturesResponse;
import data.model.ProfileResponse;
import data.model.TrackResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyRetrofitService {

  @GET(Constant.Endpoint.SAVED_ALBUM) Observable<AlbumResponse> getAlbum();

  @GET(Constant.Endpoint.GET_PROFILE_INFO) Observable<ProfileResponse> getUserProfile();

  @GET(Constant.Endpoint.ALBUM_TRACKS) Observable<TrackResponse> getTracks(
          @Path(Constant.Params.ALBUMS_ID) String artistId);

  @GET(Constant.Endpoint.SEARCH_TRACKS) Observable<TrackResponse> searchTrack(
          @Query(Constant.Params.QUERY_SEARCH) String artist);


  @GET(Constant.Endpoint.AUDIO_FEATURES) Observable<AudioFeaturesResponse> getAudioFeaturesForTrack(
          @Path(Constant.Params.TRACK_ID) String artistId);


}
