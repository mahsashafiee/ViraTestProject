package ir.maktabsharif.viratestproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.repository.Repository;

public class DetailViewModel extends AndroidViewModel {

    private Repository mRepository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);

    }

    public void getGame(){
        mRepository.getGame();
    }

    public LiveData<Game> getGameData() {
        return mRepository.getGameData();
    }


}
