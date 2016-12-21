package spit.matrix2017.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import spit.matrix2017.Fragments.AboutAppFragment;
import spit.matrix2017.Fragments.CommitteeFragment;
import spit.matrix2017.Fragments.ContactUsFragment;
import spit.matrix2017.Fragments.DevelopersFragment;
import spit.matrix2017.Fragments.FavoritesFragment;
import spit.matrix2017.Fragments.MainFragment;
import spit.matrix2017.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarLayout appBarLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    CollapsingToolbarLayout collapsingToolbarLayout;

    FragmentManager fm;
    String backStageName;
    private static final long DRAWER_DELAY = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] images = {R.drawable.codatron,
                R.drawable.laser_maze,
                R.drawable.laser_tag,
                R.drawable.virtual_stock_market,
                R.drawable.battle_frontier,
                R.drawable.escape_plan,
                R.drawable.tech_charades,
                R.drawable.tech_xplosion,
                R.drawable.no_escape,
                R.drawable.techeshis_castle,
                R.drawable.technovanza,
                R.drawable.tesseract,
                R.drawable.battle_of_brains,
                R.drawable.human_foosball,
                R.drawable.lan_gaming,
                R.drawable.lan_mafia,
                R.drawable.mind_that_word,
                R.drawable.pokemon_showdown};

        for (int i : images)
            Picasso.with(getApplicationContext()).load(i).resize(400, 400).centerCrop().fetch();

        //instantiation
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar_main);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
        appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
        layoutParams.setBehavior(appBarLayoutBehaviour);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        if (savedInstanceState == null) {
            fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            MainFragment mainFragment = MainFragment.newInstance();
            transaction.replace(R.id.fragment_container, mainFragment).commit();
        }

        setupDrawerLayout();

        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    public void setupDrawerLayout() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        drawerLayout.closeDrawers();
                        if (!item.isChecked()) {
                            final FragmentTransaction fragmentTransaction = fm.beginTransaction();
                            switch (item.getItemId()) {
                                case R.id.homepage_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            boolean isFragmentInStack = fm.popBackStackImmediate(backStageName, 0);
                                            if (!isFragmentInStack) {
                                                MainFragment fragment = MainFragment.newInstance();
                                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                                backStageName = fragment.getClass().getName();
                                                fragmentTransaction.addToBackStack(backStageName).commit();
                                            }
                                            appBarLayout.setExpanded(true, true);
                                            collapsingToolbarLayout.setTitle("Matrix 17");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.favorites_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new FavoritesFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null).commit();
                                            collapsingToolbarLayout.setTitle("Favorites");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.sponsors_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "Coming soon!", Toast.LENGTH_SHORT).show();
                                            // IMPORTANT: Remove checkable=false from the 'Sponsors' menu item in res/menu/navdrawer_menu.xml when Sponsors fragment is complete

                                            /* Delete later
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new SponsorsFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle("Sponsors");
                                            */
                                        }
                                    }, DRAWER_DELAY);
                                    //break;
                                    return true; //Delete later

                                case R.id.commitee_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new CommitteeFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle("Committee");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.developers_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new DevelopersFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle("Developers");
                                        }
                                    }, DRAWER_DELAY);
                                    break;
                                case R.id.contact_us_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new ContactUsFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle("Contact us");
                                        }
                                    }, DRAWER_DELAY);
                                    break;

                                case R.id.share_app_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent();
                                            intent.setAction(Intent.ACTION_SEND);
                                            intent.putExtra(Intent.EXTRA_TEXT, "Check out the official app for Matrix 17!\n\n" + getResources().getString(R.string.playstore_link));
                                            intent.setType("text/plain");
                                            startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_message)));
                                        }
                                    }, DRAWER_DELAY);
                                    return true;
                                case R.id.rate_app_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(getResources().getString(R.string.playstore_link)));
                                            startActivity(intent);
                                        }
                                    }, DRAWER_DELAY);
                                    return true;
                                case R.id.about_menuItem:
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            getSupportFragmentManager().popBackStackImmediate();
                                            fragmentTransaction.replace(R.id.fragment_container, new AboutAppFragment());
                                            appBarLayout.setExpanded(false, true);
                                            fragmentTransaction.addToBackStack(null);
                                            fragmentTransaction.commit();
                                            collapsingToolbarLayout.setTitle(getResources().getString(R.string.aboutapp));
                                        }
                                    }, DRAWER_DELAY);
                            }
                        }

                        AppBarLayout.LayoutParams params =
                                (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();

                        if (item.getItemId() == R.id.homepage_menuItem)

                            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);

                        else

                            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                                    | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);

                        return true;
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Uri uri = null;
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_empty:
                return true;
            case R.id.menu_visit_website:
                uri = Uri.parse(getResources().getString(R.string.matrix_website));
                break;
            case R.id.menu_follow_facebook:
                uri = Uri.parse(getResources().getString(R.string.matrix_fb_link));
                break;
            case R.id.menu_follow_twitter:
                uri = Uri.parse(getResources().getString(R.string.matrix_twit_link));
                break;
            case R.id.menu_follow_instagram:
                uri = Uri.parse(getResources().getString(R.string.matrix_insta_link));
                break;
            case R.id.menu_follow_snapchat:
                uri = Uri.parse(getResources().getString(R.string.matrix_snap_link));
                break;
        }

        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawers();
        else {
            navigationView.getMenu().getItem(0).setChecked(true);
            collapsingToolbarLayout.setTitle("Matrix 17");
            appBarLayout.setExpanded(true, true);

            super.onBackPressed();
        }
    }
}