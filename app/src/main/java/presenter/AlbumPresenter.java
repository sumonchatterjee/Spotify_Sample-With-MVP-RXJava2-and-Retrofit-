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

package presenter;

import data.model.AlbumResponse;
import interactor.AlbumInteractor;
import io.reactivex.observers.DisposableObserver;

public class AlbumPresenter extends BasePresenter<AlbumPresenter.View> {

  private AlbumInteractor interactor;

  public AlbumPresenter(AlbumInteractor interactor) {
    this.interactor = interactor;
  }


  public void getSavedAlbums(){

    DisposableObserver<AlbumResponse> disposableObserver = interactor.getAlbums().subscribeWith(new DisposableObserver<AlbumResponse>() {

      @Override
      public void onNext(AlbumResponse t) {
        getView().displayAlbums(t);
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {
      }
    });

    addDisposableObserver(disposableObserver);
  }



  @Override
  public void terminate() {
    super.terminate();
    setView(null);
  }

  public interface View extends BasePresenter.View {

    void displayAlbums(AlbumResponse album);


  }
}
