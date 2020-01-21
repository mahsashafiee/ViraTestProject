package ir.maktabsharif.viratestproject.views;

import androidx.fragment.app.Fragment;

public class HomeActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return HomeFragment.newInstance();
    }
}
