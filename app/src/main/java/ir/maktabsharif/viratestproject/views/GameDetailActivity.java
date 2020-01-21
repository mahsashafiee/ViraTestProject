package ir.maktabsharif.viratestproject.views;

import androidx.fragment.app.Fragment;

public class GameDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return GameDetailFragment.newInstance();
    }
}
