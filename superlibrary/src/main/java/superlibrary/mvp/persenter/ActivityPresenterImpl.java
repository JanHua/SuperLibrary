package superlibrary.mvp.persenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import superlibrary.mvp.helper.GenericHelper;
import superlibrary.mvp.view.IView;

/**
 * Activity主持者(presenter层)
 */
public abstract class ActivityPresenterImpl<T extends IView> extends Activity implements IPresenter<T>, View.OnClickListener, View.OnLongClickListener {

    protected T mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBefore(savedInstanceState);
        try {
            mView = getViewClass().newInstance();
            mView.bindPresenter(this);
            setContentView(mView.create(getLayoutInflater(), null));
            mView.bindEvent();
            createLate(savedInstanceState);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Class<T> getViewClass() {
        return GenericHelper.getViewClass(getClass());
    }

    @Override
    public void createBefore(Bundle saveInstance) {

    }

    @Override
    public void createLate(Bundle saveInstance) {

    }
}
