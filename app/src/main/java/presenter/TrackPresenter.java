package presenter;

import android.content.Context;
import android.widget.Toast;

import data.model.TrackResponse;
import interactor.TrackInteractor;
import io.reactivex.observers.DisposableObserver;

public class TrackPresenter extends BasePresenter<TrackPresenter.View> {

    private TrackInteractor interactor;
    private Context mContext;

    public TrackPresenter(TrackInteractor interactor, Context context) {
        this.interactor = interactor;
        this.mContext =context;
    }


// get tracks for the specified album, when user click on it
    public void getTrackForAlbums(String albumId){
        getView().showLoader();
        DisposableObserver<TrackResponse> disposableObserver = interactor.getAlbumTracks(albumId)
                .subscribeWith(new DisposableObserver<TrackResponse>() {

                    @Override
                    public void onNext(TrackResponse t) {
                        getView().hideLoader();
                        getView().displayTracksForAlbum(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoader();
                        System.out.println(e.getLocalizedMessage());
                        Toast.makeText(mContext,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        addDisposableObserver(disposableObserver);
    }

    //search particular tracks
    public void searchTracks(String albumId){
        getView().showLoader();
        DisposableObserver<TrackResponse> disposableObserver = interactor.searchTracks(albumId)
                .subscribeWith(new DisposableObserver<TrackResponse>() {

                    @Override
                    public void onNext(TrackResponse t) {
                        getView().hideLoader();
                        getView().displayTracksForAlbum(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoader();
                        System.out.println(e.getLocalizedMessage());
                        Toast.makeText(mContext,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
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

        void displayTracksForAlbum(TrackResponse tracks);

        void showLoader();
        void hideLoader();


    }
}
