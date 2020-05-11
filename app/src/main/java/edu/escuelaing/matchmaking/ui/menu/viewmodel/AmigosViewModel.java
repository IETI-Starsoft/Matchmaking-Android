package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmigosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AmigosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Amigos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
