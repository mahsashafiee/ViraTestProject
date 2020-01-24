package ir.maktabsharif.viratestproject.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class GameDetailActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context target) {

        return new Intent(target, GameDetailActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return GameDetailFragment.newInstance();
    }
}
