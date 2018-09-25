package com.bankex.pay.presentation.ui.home.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> implements FastScrollerLabelPublisher {

    List<ContactModel> mContactModelList;

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View inflate = from.inflate(R.layout.contacts_list_item, parent, false);
        return new ContactViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = mContactModelList.get(position);
        holder.name.setText(contact.getFirstName());
        String index = String.valueOf(contact.getFirstName().charAt(0)).toUpperCase();
        holder.index.setText(index);
        assembleContactPlaceholder(holder);
    }


    private void assembleContactPlaceholder(ContactViewHolder contactHolder) {
        contactHolder.index.setVisibility(TextView.VISIBLE);
        contactHolder.index.setTextColor(Color.WHITE);
        contactHolder.index.setTextSize(R.dimen.text_size_display);
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

    /**
     * Refresh contact list being displayed
     */
    public void refresh(List<ContactModel> contacts) {
        this.mContactModelList = contacts;
        notifyDataSetChanged();
    }
}
