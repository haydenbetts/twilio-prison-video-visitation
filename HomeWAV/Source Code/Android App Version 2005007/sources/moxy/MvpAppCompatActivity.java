package moxy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MvpAppCompatActivity extends AppCompatActivity implements MvpDelegateHolder {
    private MvpDelegate<? extends MvpAppCompatActivity> mvpDelegate;

    public MvpAppCompatActivity() {
    }

    public MvpAppCompatActivity(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMvpDelegate().onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        getMvpDelegate().onAttach();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getMvpDelegate().onAttach();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getMvpDelegate().onSaveInstanceState(bundle);
        getMvpDelegate().onDetach();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        getMvpDelegate().onDetach();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroyView();
        if (isFinishing()) {
            getMvpDelegate().onDestroy();
        }
    }

    public MvpDelegate getMvpDelegate() {
        if (this.mvpDelegate == null) {
            this.mvpDelegate = new MvpDelegate<>(this);
        }
        return this.mvpDelegate;
    }
}
