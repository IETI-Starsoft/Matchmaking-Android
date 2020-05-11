package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisMatchesTeamViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public MisMatchesTeamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mis matches equipo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
