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

public class CrearEquipoFragment extends Fragment{

    private CrearEquipoViewModel crearEquipoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        crearEquipoViewModel =
                ViewModelProviders.of(this).get(CrearEquipoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_equipo, container, false);
        final TextView textView = root.findViewById(R.id.text_crear_equipo);
        crearEquipoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
