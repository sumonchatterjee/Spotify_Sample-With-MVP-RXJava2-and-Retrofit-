package data.api;

import static data.api.Constant.Params.ALBUMS_ID;
import static data.api.Constant.Params.QUERY_SEARCH;
import static data.api.Constant.Params.TRACK_ID;

/**
 * Created by sumon.chatterjee on 03/04/18.
 */

public class Constant {

    public static final String CLIENT_ID = "72d38cf29b9c49eaa1feed277944fba6";
    public static final String REDIRECT_URI = "https://simpplr/callback";
    public static final String SPOTIFY_API = "https://api.spotify.com";

    public static final String API_KEY = "Authorization";


    public static final class Endpoint {

        public static final String SAVED_ALBUM = "/v1/me/albums";
        public static final String GET_PROFILE_INFO = "/v1/me";

        public static final String ALBUM_TRACKS =
                "v1/albums/{" + ALBUMS_ID + "}/tracks";

        public static final String AUDIO_FEATURES =
                "v1/audio-features/{" + TRACK_ID + "}";


        public static final String SEARCH_TRACKS = "/v1/search?type=track&market=US";


    }

    public static final class Params {
        public static final String ALBUMS_ID = "id";
        public static final String TRACK_ID = "id";
        public static final String QUERY_SEARCH = "q";
    }
}
