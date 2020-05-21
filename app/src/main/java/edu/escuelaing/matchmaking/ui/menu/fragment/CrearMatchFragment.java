package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.datepicker.MaterialDatePicker;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.CrearMatchViewModel;

public class CrearMatchFragment extends Fragment  {

    private CrearMatchViewModel crearMatchViewModel;
    private View root;
    private Context mContext;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        crearMatchViewModel =
                ViewModelProviders.of(this).get(CrearMatchViewModel.class);


        root = inflater.inflate(R.layout.fragment_crear_match, container, false);
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

        root.findViewById(R.id.suscription).setVisibility(View.INVISIBLE);
        //checkbox suscripcion
        final CheckBox checkbox = (CheckBox) root.findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = checkbox.isChecked();
                if (checked)
                    showAmount(root);
                else
                    hideAmount(root);
            }
        });
        final Button confirmar = (Button) root.findViewById(R.id.confirmar);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void showAmount(View view){
        View amount = view.findViewById(R.id.suscription);
        amount.setVisibility(View.VISIBLE);
    }

    public void hideAmount(View view){
        View amount = view.findViewById(R.id.suscription);
        amount.setVisibility(View.INVISIBLE);
    }
}
