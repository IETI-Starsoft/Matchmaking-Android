package edu.escuelaing.matchmaking.ui.menu;

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

import edu.escuelaing.matchmaking.R;

public class BuscarMatchFragment extends Fragment{

    private BuscarMatchViewModel buscarMatchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        buscarMatchViewModel =
                ViewModelProviders.of(this).get(BuscarMatchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_buarcar_match, container, false);
        final TextView textView = root.findViewById(R.id.text_buscar_match);
        buscarMatchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
