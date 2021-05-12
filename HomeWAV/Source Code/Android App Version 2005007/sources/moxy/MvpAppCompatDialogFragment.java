package moxy;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

public class MvpAppCompatDialogFragment extends AppCompatDialogFragment implements MvpDelegateHolder {
    private boolean isStateSaved;
    private MvpDelegate<? extends MvpAppCompatDialogFragment> mvpDelegate;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMvpDelegate().onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        this.isStateSaved = false;
        getMvpDelegate().onAttach();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.isStateSaved = true;
        getMvpDelegate().onSaveInstanceState(bundle);
        getMvpDelegate().onDetach();
    }

    public void onStop() {
        super.onStop();
        getMvpDelegate().onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDetach();
        getMvpDelegate().onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isFinishing()) {
            getMvpDelegate().onDestroy();
            return;
        }
        boolean z = false;
        if (this.isStateSaved) {
            this.isStateSaved = false;
            return;
        }
        Fragment parentFragment = getParentFragment();
        while (!z && parentFragment != null) {
            z = parentFragment.isRemoving();
            parentFragment = parentFragment.getParentFragment();
        }
        if (isRemoving() || z) {
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
