package edu.escuelaing.matchmaking.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransferirCreditosViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public TransferirCreditosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is transferir creditos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
