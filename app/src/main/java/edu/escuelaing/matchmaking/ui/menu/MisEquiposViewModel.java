package edu.escuelaing.matchmaking.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisEquiposViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public MisEquiposViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mis equipos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
