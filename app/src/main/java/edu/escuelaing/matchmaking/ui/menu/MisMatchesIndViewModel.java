package edu.escuelaing.matchmaking.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MisMatchesIndViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public MisMatchesIndViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mis matches ind fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
