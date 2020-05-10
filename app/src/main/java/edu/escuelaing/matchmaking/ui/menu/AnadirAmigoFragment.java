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

public class AnadirAmigoFragment extends Fragment{

    private AnadirAmigoViewModel anadirAmigoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        anadirAmigoViewModel =
                ViewModelProviders.of(this).get(AnadirAmigoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_anadir_amigo, container, false);
        final TextView textView = root.findViewById(R.id.text_anadir_amigos);
        anadirAmigoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
