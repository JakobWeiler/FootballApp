package at.footballapp.captainandroid.footballapp.pkgGUI;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import at.footballapp.captainandroid.footballapp.R;

public class StatisticActivity extends AppCompatActivity {



    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private ImageView imgViewGoalshot = null;
        private ImageView imgViewHeadgoal = null;
        private ImageView imgViewHeadgoalSnow = null;
        private ImageView imgViewNutmeg = null;
        private ImageView imgViewOwnGoal = null;
        private ImageView imgViewPenalty = null;

        private void getViews(){
            this.imgViewGoalshot = (ImageView)getActivity().findViewById(R.id.imgViewGoalshot);
            this.imgViewHeadgoal = (ImageView)getActivity().findViewById(R.id.imgViewHeadgoal);
            this.imgViewHeadgoalSnow = (ImageView)getActivity().findViewById(R.id.imgViewHeadgoalSnow);
            this.imgViewNutmeg = (ImageView)getActivity().findViewById(R.id.imgViewNutmeg);
            this.imgViewOwnGoal = (ImageView)getActivity().findViewById(R.id.imgViewOwnGoal);
            this.imgViewPenalty = (ImageView)getActivity().findViewById(R.id.imgViewPenalty);
        }
/*
        public void onImgViewCick(View view) {
                Toast t = new Toast(getActivity());

            try{
                if(getActivity().findViewById(R.id.imgViewGoalshot)==view) {
                    t = Toast.makeText(getContext(), "Goals scored", Toast.LENGTH_LONG);
                }
                else if(getActivity().findViewById(R.id.imgViewPenalty)==view) {
                    t = Toast.makeText(getActivity(), "Penalty goals", Toast.LENGTH_LONG);
                }
                else if(getActivity().findViewById(R.id.imgViewHeadgoal)==view) {
                    t = Toast.makeText(getActivity().getApplicationContext(), "Header goals", Toast.LENGTH_LONG);
                }
                else if(getActivity().findViewById(R.id.imgViewHeadgoalSnow)==view) {
                    t = Toast.makeText(getActivity().getApplicationContext(), "Header goals snow", Toast.LENGTH_LONG);
                }
                else if(getActivity().findViewById(R.id.imgViewOwnGoal)==view) {
                    t = Toast.makeText(getActivity().getApplicationContext(), "Own goals", Toast.LENGTH_LONG);
                }
                else if(getActivity().findViewById(R.id.imgViewNutmeg)==view) {
                    t = Toast.makeText(getActivity().getApplicationContext(), "Nutmegs", Toast.LENGTH_LONG);
                }
                t.show();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
*/
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_statistic, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Team 1";
                case 1:
                    return "Team 2";
            }
            return null;
        }




    }
}
