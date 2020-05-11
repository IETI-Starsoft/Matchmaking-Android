package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.IOException;
import java.net.URL;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.PerfilViewModel;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        Storage storage = new Storage(getContext());
        final RetrofitNetwork retrofitNetwork = new RetrofitNetwork(storage.getToken());
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        perfilViewModel.setEmail(storage.getEmail());
        perfilViewModel.setRetrofitNetwork(retrofitNetwork);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        final TextView textView = root.findViewById(R.id.name_perfil);
        final ImageView imageView = root.findViewById(R.id.userImage);
        final RatingBar ratingBar = root.findViewById(R.id.ratingBar);
        ratingBar.setIsIndicator(true);
        perfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        perfilViewModel.getUrl().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap image) {
                imageView.setImageBitmap(image);
            }
        });

        perfilViewModel.getUserRating().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ratingBar.setRating(integer);
            }
        });
        return root;
    }

}
