package edu.escuelaing.matchmaking.ui.menu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;

import edu.escuelaing.matchmaking.R;
import edu.escuelaing.matchmaking.network.RetrofitNetwork;
import edu.escuelaing.matchmaking.pojo.Team;
import edu.escuelaing.matchmaking.storage.Storage;
import edu.escuelaing.matchmaking.ui.menu.viewmodel.CrearMatchViewModel;

public class CrearMatchFragment extends Fragment  {

    private CrearMatchViewModel crearMatchViewModel;
    private View root;
    private Context mContext;
    private ArrayList<View> teams;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        crearMatchViewModel =
                ViewModelProviders.of(this).get(CrearMatchViewModel.class);

        teams = new ArrayList<>();
        Storage storage = new Storage(getContext());
        final RetrofitNetwork retrofitNetwork = new RetrofitNetwork(storage.getToken());
        crearMatchViewModel.setEmail(storage.getEmail());
        crearMatchViewModel.setRetrofitNetwork(retrofitNetwork);
        root = inflater.inflate(R.layout.fragment_crear_match, container, false);
        root.findViewById(R.id.suscription).setVisibility(View.INVISIBLE);

        final EditText activity = root.findViewById(R.id.Input_Activity);
        final EditText time = root.findViewById(R.id.Time);
        final EditText date = root.findViewById(R.id.Date);
        final EditText location = root.findViewById(R.id.Location);
        final EditText description = root.findViewById(R.id.Description);
        final EditText suscription = root.findViewById(R.id.suscription);
        final NestedScrollView scrollTeams = root.findViewById(R.id.ScrollTeams);
        final LinearLayout teamContainer = root.findViewById(R.id.teams_container);

        scrollTeams.setVisibility(View.INVISIBLE);
        final CheckBox checkbox = (CheckBox) root.findViewById(R.id.checkbox);

        //teamContainer.addChildrenForAccessibility(teams);

        final CheckBox checkgrupal = (CheckBox) root.findViewById(R.id.checkGrupal);
        final CheckBox checkind = (CheckBox) root.findViewById(R.id.checkIndividual);
        final Button confirmar = (Button) root.findViewById(R.id.confirmar);


        //checkbox suscripcion
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
        //checkbox grupal
        checkgrupal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkind.isChecked())checkind.setChecked(false);
                if (checkgrupal.isChecked()) {
                    if (teams.size() !=  getTeams().size()){
                        teams = getTeams();
                        fillTeamContainer(teamContainer);
                    }
                    scrollTeams.setVisibility(View.VISIBLE);
                    crearMatchViewModel.setParticipants("GroupActivity");
                }
                else {
                    scrollTeams.setVisibility(View.INVISIBLE);
                    crearMatchViewModel.setParticipants(null);
                }
            }
        });
        //checkbox individual
        checkind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkgrupal.isChecked()) {
                    scrollTeams.setVisibility(View.INVISIBLE);
                    checkgrupal.setChecked(false);
                }
                if (checkind.isChecked()) crearMatchViewModel.setParticipants("IndividualActivity");
                else crearMatchViewModel.setParticipants(null);
            }
        });
        //boton confirmar
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (activity.getText().length()!=0 && location.getText().length()!=0
                        && date.getText().length()!=0 && time.getText().length()!=0){
                    if(checkbox.isChecked()){
                        if(suscription.getText().length()!=0){
                            validateParticipants();
                        }
                    }
                    else validateParticipants();
                }
            }
        });
        return root;
    }

    private void fillTeamContainer(LinearLayout teamContainer) {
        for(View team: teams){
            teamContainer.addView(team);
        }
    }

    public void validateGroup(){
        int cont = 0;
        for (View view: teams){
            CheckBox temp = (CheckBox) view;
            if (temp.isChecked()) {
                crearMatchViewModel.setIndexTeam(teams.indexOf(view));
                cont++;
            }
        }
        if (cont==1) postActiivty(false);
    }

    public void validateParticipants(){
        if (crearMatchViewModel.getParticipants().getValue().equals("IndividualActivity")){
            postActiivty(true);
        }
        else if (crearMatchViewModel.getParticipants().getValue().equals("GroupActivity")){
            validateGroup();
        }
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

    public void postActiivty(boolean type){
        EditText activity = root.findViewById(R.id.Input_Activity);
        EditText time = root.findViewById(R.id.Time);
        EditText date = root.findViewById(R.id.Date);
        EditText location = root.findViewById(R.id.Location);
        EditText description = root.findViewById(R.id.Description);
        EditText suscription = root.findViewById(R.id.suscription);
        crearMatchViewModel.setActivity(activity.getText().toString());
        crearMatchViewModel.setTime(time.getText().toString());
        crearMatchViewModel.setDate(date.getText().toString());
        crearMatchViewModel.setLocation(location.getText().toString());
        crearMatchViewModel.setDescription(description.getText().toString());
        crearMatchViewModel.setBet(suscription.getText().toString());
        if (type) crearMatchViewModel.createIndividualActivity();
        else crearMatchViewModel.createGroupActivity();
    }

    public ArrayList<View> getTeams(){
        ArrayList<View> teams = new ArrayList<>();
        for (Team team: crearMatchViewModel.getUserTeams()){
              CheckBox temp = new CheckBox(mContext);
              temp.setText(team.getName());
              teams.add(temp);
        }
        return teams;
    }
}
