package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.User;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.EditarPerfilViewModel;
import edu.escuelaing.matchmaking.ui.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Response;

public class EditarPerfilFragment extends Fragment{

    private EditarPerfilViewModel editarPerfilViewModel;

    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Storage storage = new Storage(getContext());
        final RetrofitNetwork retrofitNetwork = new RetrofitNetwork(storage.getToken());
        editarPerfilViewModel =
                ViewModelProviders.of(this).get(EditarPerfilViewModel.class);
        editarPerfilViewModel.setRetrofitNetwork(retrofitNetwork);
        editarPerfilViewModel.setUserEmail(storage.getEmail());
        final View root = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        final TextInputEditText userNameText = root.findViewById(R.id.editName);
        final AppCompatEditText userBioText = root.findViewById(R.id.editBio);
        Button updateButton = root.findViewById(R.id.update_button);

        editarPerfilViewModel.getUserNameText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                userNameText.setHint(s);
            }
        });

        editarPerfilViewModel.getUserBioText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
               userBioText.setHint(s);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String name = userNameText.getText().toString();
                final String bio = userBioText.getText().toString();
                if (name.isEmpty() && bio.isEmpty() ) {
                    userNameText.setError(getString(R.string.campo_vacio));
                    userBioText.setError(getString(R.string.campo_vacio));
                }
                else {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            String info = "¡Datos actualizados!";
                            Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.drawer_layout),
                                    info, Snackbar.LENGTH_LONG);
                            try {
                                editarPerfilViewModel.updateUserData(name, bio);
                            } catch (IOException e) {
                                e.printStackTrace();
                                snackBar.setText("Error actualizando datos.");
                                View snackbarView = snackBar.getView();
                                snackbarView.setBackgroundColor(getResources().getColor(R.color.errorSnackbar));
                            }
                            snackBar.show();
                        }
                    });

                }
            }
        });

        return root;
    }


}
