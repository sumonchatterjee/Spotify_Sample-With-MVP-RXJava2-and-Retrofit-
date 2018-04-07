package presenter;

import android.content.Context;
import android.widget.Toast;
import data.model.ProfileResponse;
import interactor.ProfileInteractor;
import io.reactivex.observers.DisposableObserver;


public class ProfilePresenter extends BasePresenter<ProfilePresenter.View> {


    private ProfileInteractor interactor;
    private Context mContext;

    public ProfilePresenter(ProfileInteractor interactor, Context context) {
        this.interactor = interactor;
        this.mContext =context;
    }


    public void getUserProfile(){
        getView().showLoader();
        DisposableObserver<ProfileResponse> disposableObserver = interactor.getUserProfile()
                .subscribeWith(new DisposableObserver<ProfileResponse>() {

            @Override
            public void onNext(ProfileResponse t) {
                getView().hideLoader();
                getView().displayProfile(t);
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

        void displayProfile(ProfileResponse profile);


    }
}
