package edu.escuelaing.matchmaking.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CrearEquipoViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public CrearEquipoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is crear equipo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
