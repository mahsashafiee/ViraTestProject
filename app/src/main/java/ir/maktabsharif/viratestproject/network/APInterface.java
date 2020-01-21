package ir.maktabsharif.viratestproject.network;

import java.util.List;

import ir.maktabsharif.viratestproject.models.Game;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APInterface {

    @GET("bins/pnfbu")
    Call<List<Game>> getGames();

    @GET("bins/1bjyoa")
    Call<Game> getGame();
}
