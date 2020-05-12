package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnadirAmigoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnadirAmigoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is añadir amigos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
