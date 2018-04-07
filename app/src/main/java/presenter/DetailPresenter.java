package presenter;

import android.content.Context;
import android.widget.Toast;

import data.model.AudioFeaturesResponse;
import interactor.DetailInteractor;
import io.reactivex.observers.DisposableObserver;

public class DetailPresenter extends BasePresenter<DetailPresenter.View> {


    private DetailInteractor interactor;
    private Context mContext;

    public DetailPresenter(DetailInteractor interactor, Context context) {
        this.interactor = interactor;
        this.mContext =context;
    }


    //get audio featues for a particular track
    public void getAudioFeaturesForTrack(String trackId){
        getView().showLoader();
        DisposableObserver<AudioFeaturesResponse> disposableObserver = interactor.getAudioFeaturesForTrack(trackId)
                .subscribeWith(new DisposableObserver<AudioFeaturesResponse>() {

                    @Override
                    public void onNext(AudioFeaturesResponse t) {
                        getView().hideLoader();
                        getView().showAudioFeatures(t);
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

        void showLoader();

        void hideLoader();

        void showAudioFeatures(AudioFeaturesResponse audioFeaturesResponse);


    }
}
