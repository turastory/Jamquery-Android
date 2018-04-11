package com.turastory.jamquery.presentation.ui.jamquery_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turastory.jamquery.R;
import com.turastory.jamquery.presentation.vo.JamqueryVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tura on 2018-04-11.
 * <p>
 * Jamquery 리스트를 보여주는 어댑터.
 */
public class JamqueryListAdapter extends RecyclerView.Adapter<JamqueryListAdapter.ViewHolder> {
    
    private List<JamqueryVO> jamqueries;
    
    public JamqueryListAdapter() {
        this.jamqueries = new ArrayList<>();
    }
    
    public void setJamqueries(List<JamqueryVO> newJamqueries) {
        this.jamqueries = newJamqueries;
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_jamquery, parent, false));
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(jamqueries.get(position));
    }
    
    @Override
    public int getItemCount() {
        return jamqueries.size();
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        
        @BindView(R.id.jamquery_item_date)
        TextView dateText;
        @BindView(R.id.jamquery_item_title)
        TextView titleText;
        
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        
        public void bind(JamqueryVO jamqueryVO) {
            dateText.setText(JamqueryDateFormatter.format(jamqueryVO.getDate()));
            titleText.setText(jamqueryVO.getTitle());
        }
    }
}
