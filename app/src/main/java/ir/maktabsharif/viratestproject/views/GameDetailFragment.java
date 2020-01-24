package ir.maktabsharif.viratestproject.views;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import ir.maktabsharif.viratestproject.R;
import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.viewmodels.DetailViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment {

    private VideoView mVideoView;
    private DetailViewModel mViewModel;
    private ImageView mGamePoster;
    private TextView mGameTitle, mGameDescription, mGameRate, mGamePlayersCount, mGameGenre;

    private int mCurrentPosition = 0;


    private static final String PLAYBACK_TIME = "play_time";

    public GameDetailFragment() {
        // Required empty public constructor
    }

    public static GameDetailFragment newInstance() {

        Bundle args = new Bundle();

        GameDetailFragment fragment = new GameDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);

        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mViewModel.getGame();
        mViewModel.getGameData().observe(this, game -> {
            initializePlayer(game);
            initViews(game);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_game_detail, container, false);

        findViews(root);

        MediaController controller = new MediaController(getContext());
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);

        return root;
    }

    private void initViews(Game game) {
        Picasso.get().load(game.getImageURL())
                .placeholder(R.drawable.product_image_placeholder)
                .into(mGamePoster);
        mGameTitle.setText(game.getTitle());
        mGameDescription.setText(getActivity().getResources().getString(R.string.description, game.getDescription()));
        mGameRate.setText(getActivity().getResources().getString(R.string.rate, game.getRate()));
        mGamePlayersCount.setText(getActivity().getResources().getQuantityString(R.plurals.players_count,
                game.getPlayersCount(), game.getPlayersCount()));
        mGameGenre.setText(getActivity().getResources().getString(R.string.genre, game.getGenre().getName()));
    }

    private void findViews(View root) {
        mVideoView = root.findViewById(R.id.video_view);
        mGamePoster = root.findViewById(R.id.game_detail_poster);
        mGameTitle = root.findViewById(R.id.game_detail_title);
        mGameRate = root.findViewById(R.id.game_detail_rate);
        mGameDescription = root.findViewById(R.id.game_detail_description);
        mGamePlayersCount = root.findViewById(R.id.game_detail_players_count);
        mGameGenre = root.findViewById(R.id.game_detail_genre);
    }


    @Override
    public void onPause() {
        super.onPause();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        releasePlayer();
    }

    private void initializePlayer(Game game) {

        mVideoView.setVideoURI(Uri.parse(game.getVideoURL()));

        mVideoView.setOnPreparedListener(
                mediaPlayer -> {
                    if (mCurrentPosition > 0) {
                        mVideoView.seekTo(mCurrentPosition);
                    } else {
                        mVideoView.seekTo(1);
                    }
                    mVideoView.start();

                });

        mVideoView.setOnCompletionListener(
                mediaPlayer -> {
                    Toast.makeText(getContext(),
                            "toast",
                            Toast.LENGTH_SHORT).show();

                    // Return the video position to the start.
                    mVideoView.seekTo(0);
                });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PLAYBACK_TIME, mCurrentPosition);
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }
}
