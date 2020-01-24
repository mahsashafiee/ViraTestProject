package ir.maktabsharif.viratestproject.views;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.maktabsharif.viratestproject.R;
import ir.maktabsharif.viratestproject.adapters.GamesAdapter;
import ir.maktabsharif.viratestproject.models.Game;
import ir.maktabsharif.viratestproject.viewmodels.HomeViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel mHomeViewModel;
    private RecyclerView mRecyclerView;
    private GamesAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mHomeViewModel.getGames();

        mRecyclerView = root.findViewById(R.id.home_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new GamesAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        mHomeViewModel.getGamesData().observe(this, games -> mAdapter.setGames(games));

        return root;
    }

}
