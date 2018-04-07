/**
 * Copyright 2015 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
