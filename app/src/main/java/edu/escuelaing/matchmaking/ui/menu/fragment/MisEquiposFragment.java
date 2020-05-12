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
import edu.escuelaing.matchmaking.ui.menu.viewmodel.MisEquiposViewModel;

public class MisEquiposFragment extends Fragment{

    private MisEquiposViewModel misEquiposViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        misEquiposViewModel =
                ViewModelProviders.of(this).get(MisEquiposViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mis_equipos, container, false);
        final TextView textView = root.findViewById(R.id.text_mis_equipos);
        misEquiposViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
