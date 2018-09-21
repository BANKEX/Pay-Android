package com.bankex.pay.presentation.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.domain.models.ContactModel;
import com.bankex.pay.presentation.ui.home.adapter.holder.ContactViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.stickyindex.view.FastScrollerLabelPublisher;

/**
 * Адаптер для списка контктов
 *
 * @author Denis Anisimov.
 */
public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FastScrollerLabelPublisher {

    List<ContactModel> mContactModelList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View inflate = from.inflate(R.layout.contacts_list_item, parent, false);
        return new ContactViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mContactModelList.size();
    }

    @NotNull
    @Override
    public String getLabel(int i) {
        ContactModel contactModel = mContactModelList.get(i);
        String firstName = contactModel.getFirstName();
        char c = firstName.charAt(0);
        return String.valueOf(c).toUpperCase();
    }
}
