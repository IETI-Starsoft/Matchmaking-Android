package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Activity;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.menu.fragment.adapters.ActivityAdapter;
import edu.escuelaing.matchmaking.ui.menu.fragment.adapters.TeamAdapter;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.BuscarMatchViewModel;

public class BuscarMatchFragment extends Fragment{

    private BuscarMatchViewModel buscarMatchViewModel;

    private RecyclerView recyclerView;

    private ActivityAdapter activityAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Storage storage = new Storage(getContext());
        final RetrofitNetwork retrofitNetwork = new RetrofitNetwork(storage.getToken());
        buscarMatchViewModel =
                ViewModelProviders.of(this).get(BuscarMatchViewModel.class);
        buscarMatchViewModel.setEmail(storage.getEmail());
        buscarMatchViewModel.setRetrofitNetwork(retrofitNetwork);
        View root = inflater.inflate(R.layout.fragment_buscar_match, container, false);
        recyclerView = root.findViewById(R.id.matchesRecyclerView);
        activityAdapter = new ActivityAdapter();
        configureRecyclerView();
        buscarMatchViewModel.getActivities().observe(getViewLifecycleOwner(), new Observer<List<Activity>>() {
            @Override
            public void onChanged(List<Activity> activities) {
                activityAdapter = new ActivityAdapter(activities);
                recyclerView.setAdapter(activityAdapter);
            }
        });
        return root;
    }

    private void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(activityAdapter);
    }
}