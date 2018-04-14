
package presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract class BasePresenter<T extends BasePresenter.View> {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  private T view;

  public T getView() {
    return view;
  }

  public void setView(T view) {
    this.view = view;
  }

  public void initialize() {

  }

  public void terminate() {
    dispose();
  }

  void addDisposableObserver(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  private void dispose() {
    if (!compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public interface View {

    Context context();
  }
}