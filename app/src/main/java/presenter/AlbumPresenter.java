
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
