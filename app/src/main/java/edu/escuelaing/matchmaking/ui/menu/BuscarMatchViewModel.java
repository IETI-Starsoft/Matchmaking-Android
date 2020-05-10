package edu.escuelaing.matchmaking.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuscarMatchViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public BuscarMatchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is buscar match fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
