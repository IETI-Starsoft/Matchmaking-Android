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
import edu.escuelaing.matchmaking.ui.menu.viewmodel.TransferirCreditosViewModel;

public class TransferirCreditosFragment extends Fragment{

    private TransferirCreditosViewModel transferirCreditosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transferirCreditosViewModel =
                ViewModelProviders.of(this).get(TransferirCreditosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transferir_creditos, container, false);
        final TextView textView = root.findViewById(R.id.text_transferir_creditos);
        transferirCreditosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
