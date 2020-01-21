package ir.maktabsharif.viratestproject.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.network.APInterface;
import ir.maktabsharif.viratestproject.network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static final String TAG = "Repository";

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static Repository sInstance;
    private MutableLiveData<List<Game>> mGamesData = new MutableLiveData<>();
    private MutableLiveData<Game> mGameData = new MutableLiveData<>();

    public static Repository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new Repository(context);
        return sInstance;
    }

    private Repository(Context context) {
        mContext = context;
    }


    public void getGames() {
        Call<List<Game>> call = ServiceGenerator.createService(APInterface.class, mContext).getGames();
        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful())
                    mGamesData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                Log.e(TAG, "onFailure: Games ", t);
            }
        });
    }

    public void getGame() {
        Call<Game> call = ServiceGenerator.createService(APInterface.class, mContext).getGame();
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (response.isSuccessful())
                    mGameData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e(TAG, "onFailure: Game ", t);
            }
        });
    }


    public LiveData<List<Game>> getGamesData() {
        return mGamesData;
    }

    public LiveData<Game> getGameData() {
        return mGameData;
    }
}
