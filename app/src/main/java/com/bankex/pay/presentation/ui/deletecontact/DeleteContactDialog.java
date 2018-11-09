package com.bankex.pay.presentation.ui.deletecontact;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.deletecontact.DeleteContactInjector;
import com.bankex.pay.presentation.navigation.home.IMainRouter;
import com.bankex.pay.presentation.presenter.DeleteContactPresenter;
import javax.inject.Inject;

//@FragmentWithArgs
public class DeleteContactDialog extends AppCompatDialogFragment implements IDeleteContactView {
	//@Arg String mContactId;
	private String mContactId;

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
		//FragmentArgs.inject(this);

		Bundle mArgs = getArguments();
		mContactId = mArgs.getString("contact_id");
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		//setCancelable(false);
		View view = inflater.inflate(R.layout.dialog_delete_contact, container, false);
		mBinder = ButterKnife.bind(this, view);

		if (getDialog() != null && getDialog().getWindow() != null) {
			getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		}

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
		Toast.makeText(getContext(), "TETETETETETETE", Toast.LENGTH_SHORT).show();
		dismissAllowingStateLoss();
		mMainRouter.goToContactsTab(getActivity());
	}

	@Override public void showMessage() {
		Toast.makeText(getDialog().getContext(), "TETETETETETETE", Toast.LENGTH_SHORT).show();
	}
}
