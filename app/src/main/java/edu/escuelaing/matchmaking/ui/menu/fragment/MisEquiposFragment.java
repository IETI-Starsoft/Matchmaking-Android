package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.menu.fragment.adapters.TeamAdapter;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.MisEquiposViewModel;

public class MisEquiposFragment extends Fragment{

    private MisEquiposViewModel misEquiposViewModel;

    private RecyclerView recyclerView;

    private TeamAdapter teamAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Storage storage = new Storage(getContext());
        final RetrofitNetwork retrofitNetwork = new RetrofitNetwork(storage.getToken());

        misEquiposViewModel =
                ViewModelProviders.of(this).get(MisEquiposViewModel.class);

        misEquiposViewModel.setEmail(storage.getEmail());
        misEquiposViewModel.setRetrofitNetwork(retrofitNetwork);

        View root = inflater.inflate(R.layout.fragment_mis_equipos, container, false);
        recyclerView = root.findViewById(R.id.teamsRecyclerView);
        teamAdapter = new TeamAdapter();
        configureRecyclerView();

        misEquiposViewModel.getTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                teamAdapter = new TeamAdapter(teams);
                recyclerView.setAdapter(teamAdapter);
            }
        });


        return root;
    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teamAdapter);
    }
}
