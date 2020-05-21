package edu.escuelaing.matchmaking.ui.menu.fragment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.pojo.Activity;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<Activity> activities;

    public ActivityAdapter() {
    }

    public ActivityAdapter(List<Activity> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivityAdapter.ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.match_row, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Activity activity = activities.get( position );
        System.out.println(holder.name);
        holder.name.setText(activity.getType());
        holder.desc.setText(activity.getDescription());
        holder.ret.setText(activity.getOwner());
        holder.date.setText(activity.getDate());
        holder.bet.setText(String.valueOf(activity.getBet()));
    }

    @Override
    public int getItemCount() {
        return activities != null ? activities.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView desc;
        TextView date;
        TextView ret;
        TextView bet;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.match_name);
            desc = itemView.findViewById(R.id.match_desc);
            date = itemView.findViewById(R.id.match_fecha);
            ret = itemView.findViewById(R.id.match_ret);
            bet = itemView.findViewById(R.id.match_apu);
        }
    }
}
