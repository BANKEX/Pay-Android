package com.bankex.pay.presentation.ui.deletecontact;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.deletecontact.DeleteContactInjector;
import com.bankex.pay.presentation.navigation.home.IMainRouter;
import com.bankex.pay.presentation.presenter.DeleteContactPresenter;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import javax.inject.Inject;

// TODO make base fragmentDialog
@FragmentWithArgs
public class DeleteContactDialog extends MvpAppCompatDialogFragment implements IDeleteContactView {
	@Arg String mContactId;

	@Inject
	IMainRouter mMainRouter;

	@Inject
	@InjectPresenter
	DeleteContactPresenter mDeleteContactPresenter;

	private Unbinder mBinder;

	@ProvidePresenter
	public DeleteContactPresenter providePresenter() {
		return mDeleteContactPresenter;
	}

	@Override public void onCreate(@Nullable Bundle savedInstanceState) {
		DeleteContactInjector.getDeleteContactComponent().inject(this);
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this);
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		setCancelable(false);
		View view = inflater.inflate(R.layout.dialog_delete_contact, container, false);
		mBinder = ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onDetach() {
		super.onDetach();
		mBinder.unbind();
	}

	@Override public void onDestroy() {
		super.onDestroy();
		DeleteContactInjector.clearDeleteContactComponent();
	}

	@OnClick(R.id.btn_confirm_deletion)
	public void onConfirmDeletionClicked() {
		mDeleteContactPresenter.onDeleteClicked(mContactId);
	}

	@OnClick(R.id.btn_cancel_deletion)
	public void onCancelDeletionClicked() {
		dismissAllowingStateLoss();
	}

	@Override public void openContactsListFragment() {
		mMainRouter.goToContactsTab(getActivity());
		dismissAllowingStateLoss();
	}

	@Override public void showMessage(@StringRes int messageId) {
		Toast.makeText(getContext(), getString(messageId), Toast.LENGTH_SHORT).show();
	}
}
