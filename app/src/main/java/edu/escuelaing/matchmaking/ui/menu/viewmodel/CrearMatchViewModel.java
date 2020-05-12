package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CrearMatchViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public CrearMatchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is crear match fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
