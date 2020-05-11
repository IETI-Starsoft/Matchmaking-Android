package edu.escuelaing.matchmaking.ui.menu.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditarPerfilViewModel extends ViewModel{

    private MutableLiveData<String> mText;

    public EditarPerfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is editar perfil fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
