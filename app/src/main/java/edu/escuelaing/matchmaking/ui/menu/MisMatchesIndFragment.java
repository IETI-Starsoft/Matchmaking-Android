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

public class MisMatchesIndFragment extends Fragment{

    private MisMatchesIndViewModel misMatchesIndViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        misMatchesIndViewModel =
                ViewModelProviders.of(this).get(MisMatchesIndViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mis_matches_individual, container, false);
        final TextView textView = root.findViewById(R.id.text_mis_matches_individual);
        misMatchesIndViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
