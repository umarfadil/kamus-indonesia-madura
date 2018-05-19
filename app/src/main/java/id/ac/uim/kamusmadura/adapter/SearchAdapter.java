package id.ac.uim.kamusmadura.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.ac.uim.kamusmadura.R;
import id.ac.uim.kamusmadura.data.model.KamusModel;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private ArrayList<KamusModel> list = new ArrayList<>();

    public SearchAdapter() {
    }

    public void replaceAll(ArrayList<KamusModel> items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.items_kata, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
