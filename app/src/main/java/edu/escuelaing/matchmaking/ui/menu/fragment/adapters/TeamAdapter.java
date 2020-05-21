package edu.escuelaing.matchmaking.ui.menu.fragment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.pojo.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private List<Team> teams;

    public TeamAdapter(){

    }

    public TeamAdapter(List<Team> teams){
        this.teams = teams;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.team_row, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = teams.get( position );
        holder.title.setText(team.getName());
        holder.ratingBar.setRating(team.getRating().floatValue());
    }

    @Override
    public int getItemCount() {
        return teams != null ? teams.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RatingBar ratingBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            ratingBar = itemView.findViewById(R.id.teamRatingBar);
        }
    }

}
