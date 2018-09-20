package com.bankex.pay.presentation.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bankex.pay.domain.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.stickyindex.view.FastScrollerLabelPublisher;

/**
 * @author Denis Anisimov.
 */
public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FastScrollerLabelPublisher {

    List<ContactModel> mContactModelList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NotNull
    @Override
    public String getLabel(int i) {
        return null;
    }
}
