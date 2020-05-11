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

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.CrearMatchViewModel;

public class CrearMatchFragment extends Fragment{

    private CrearMatchViewModel crearMatchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        crearMatchViewModel =
                ViewModelProviders.of(this).get(CrearMatchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_match, container, false);
        final TextView textView = root.findViewById(R.id.text_crear_match);
        crearMatchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
