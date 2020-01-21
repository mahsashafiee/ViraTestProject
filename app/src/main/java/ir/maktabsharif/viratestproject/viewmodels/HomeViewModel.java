package ir.maktabsharif.viratestproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.repository.Repository;

public class HomeViewModel extends AndroidViewModel {

    private Repository mRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
    }

    public void getGames(){
        mRepository.getGames();
    }

    public LiveData<List<Game>> getGamesData() {
        return mRepository.getGamesData();
    }
}
