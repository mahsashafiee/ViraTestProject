package ir.maktabsharif.viratestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.maktabsharif.viratestproject.R;
import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.views.GameDetailActivity;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {

    private List<Game> mGames;
    private Context mContext;

    public GamesAdapter(Context context) {
        mGames = new ArrayList<>();
        mContext = context;
    }

    public void setGames(List<Game> games) {
        mGames = games;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_games_list, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        holder.bind(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mGamePoster;
        private TextView mGameTitle, mGameDescription, mGameRate, mGamePlayersCount, mGameGenre;
        private Game mGame;

        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mGamePoster = itemView.findViewById(R.id.item_games_poster);
            mGameTitle = itemView.findViewById(R.id.item_games_title);
            mGameRate = itemView.findViewById(R.id.item_games_rate);
            mGameDescription = itemView.findViewById(R.id.item_games_description);
            mGamePlayersCount = itemView.findViewById(R.id.item_games_player_count);
            mGameGenre = itemView.findViewById(R.id.item_games_genre);

        }

        void bind(Game game) {
            mGame = game;
            Picasso.get().load(game.getImageURL())
                    .placeholder(R.drawable.product_image_placeholder)
                    .into(mGamePoster);
            mGameTitle.setText(game.getTitle());
            mGameDescription.setText(mContext.getResources().getString(R.string.description, game.getDescription()));
            mGameRate.setText(mContext.getResources().getString(R.string.rate, game.getRate()));
            mGamePlayersCount.setText(mContext.getResources().getQuantityString(R.plurals.players_count,
                    game.getPlayersCount(), game.getPlayersCount()));
            mGameGenre.setText(mContext.getResources().getString(R.string.genre, game.getGenre().getName()));
        }

        @Override
        public void onClick(View v) {
            mContext.startActivity(GameDetailActivity.newIntent(mContext));
        }
    }
}
