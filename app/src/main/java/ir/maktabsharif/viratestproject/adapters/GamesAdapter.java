package ir.maktabsharif.viratestproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.maktabsharif.viratestproject.R;
import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.utils.SquareImageView;

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

    public class GamesViewHolder extends RecyclerView.ViewHolder {

        private SquareImageView mGamePoster;
        private TextView mGameTitle;
        private Game mGame;

        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);

            mGamePoster = itemView.findViewById(R.id.item_games_image);
            mGameTitle = itemView.findViewById(R.id.item_games_title);

        }

        public void bind(Game game) {
            mGame = game;
            Picasso.get().load(game.getImageURL())
                    .placeholder(R.drawable.product_image_placeholder)
                    .into(mGamePoster);
            mGameTitle.setText(game.getTitle());
        }
    }
}
