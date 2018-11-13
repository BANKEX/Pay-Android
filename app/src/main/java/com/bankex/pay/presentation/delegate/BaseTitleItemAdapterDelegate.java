package com.bankex.pay.presentation.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bankex.pay.R;
import com.bankex.pay.domain.model.BaseBankexModel;
import com.bankex.pay.domain.model.BaseTitleModel;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.ui.viewholder.base.BaseTitleViewHolder;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import java.util.List;

/**
 * Delegate for displaying base title and add button.
 */
public class BaseTitleItemAdapterDelegate extends AbsListItemAdapterDelegate<BaseTitleModel, BaseBankexModel, BaseTitleViewHolder> {
	private final Context mContext;
	private final IWalletRouter mRouter;
	private final LayoutInflater mInflater;

	public BaseTitleItemAdapterDelegate(Context context, IWalletRouter router) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mRouter = router;
	}

	@Override
	protected boolean isForViewType(@NonNull BaseBankexModel item, @NonNull List<BaseBankexModel> items, int position) {
		return item instanceof BaseTitleModel;
	}

	@NonNull
	@Override
	protected BaseTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
		return new BaseTitleViewHolder(mInflater.inflate(R.layout.item_base_title, parent, false));
	}

	@Override
	protected void onBindViewHolder(@NonNull BaseTitleModel item, @NonNull BaseTitleViewHolder viewHolder, @NonNull List<Object> payloads) {
		viewHolder.getTitleTextView().setText(item.getTitle());
		viewHolder.getAddTextView().setText(item.getAddButtonTitle());
		viewHolder.getAddTextView().setVisibility(View.VISIBLE);
		viewHolder.getAddTextView().setOnClickListener(getAddClickListener());
	}

	private View.OnClickListener getAddClickListener() {
		return view -> Toast.makeText(mContext, "coming soon", Toast.LENGTH_SHORT).show();
	}
}
