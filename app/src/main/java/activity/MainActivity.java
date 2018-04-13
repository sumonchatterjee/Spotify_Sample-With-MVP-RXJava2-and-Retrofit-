package activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.GlideImageLoader;
import data.api.client.SpotifyClient;
import data.model.Album;
import data.model.AlbumImages;
import data.model.AlbumResponse;
import data.model.ProfileResponse;
import fragment.BaseFragment;
import fragment.TrackFragment;
import interactor.AlbumInteractor;
import interactor.ProfileInteractor;
import presenter.AlbumPresenter;
import presenter.ProfilePresenter;
import spotify.com.spotifysample.R;



public class MainActivity extends BaseActivity implements ProfilePresenter.View,AlbumPresenter.View,NavigationView.OnNavigationItemSelectedListener,
        BaseFragment.OnFragmentInteractionListener{

    private ProfilePresenter profilePresenter;
    AlbumPresenter albumPresenter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String albumId;
    private Album albumDetail;
    private  Toolbar topToolBar;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.dineoutLoader) ProgressBar bar;
    @BindView(R.id.nv) NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawers_layout);
        ButterKnife.bind(this);

        profilePresenter = new ProfilePresenter(new ProfileInteractor(new SpotifyClient()),this);
        profilePresenter.setView(this);

        albumPresenter=new AlbumPresenter(new AlbumInteractor(new SpotifyClient()));
        albumPresenter.setView(this);

        navigationView.setNavigationItemSelectedListener(this);

        topToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setTitle("Spotify");
        toggleDrawer();
        profilePresenter.getUserProfile();
        albumPresenter.getSavedAlbums();

    }


     public Toolbar getToolbar(){
        return topToolBar;
     }
    private void toggleDrawer(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        //mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {

            showAlbumDetails();
            mDrawerLayout.closeDrawer(GravityCompat.START);

        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public void displayProfile(ProfileResponse profile) {
      if(profile!=null){
          ((TextView)findViewById(R.id.profile_name)).setText(profile.getDisplay_name());
          ((TextView)findViewById(R.id.profile_email)).setText(profile.getEmail());
          ((TextView)findViewById(R.id.profile_bday)).setText(profile.getBirthdate());
          ((TextView)findViewById(R.id.profile_country)).setText(profile.getCountry());


          List<AlbumImages> image =profile.getImages();
          if(image!=null && image.size()>0){
              GlideImageLoader.imageLoadRequest(((ImageView)findViewById(R.id.circleView)),image.get(0).getUrl(), R.mipmap.icon);
          }
      }


    }


    @Override
    public void displayAlbums(AlbumResponse album) {
      if(album!=null){
          albumDetail = album.getItems().get(0).getAlbum();
          albumId = album.getItems().get(0).getAlbum().getId();
          NavigationView navView = findViewById(R.id.nv);

          Menu menu = navView.getMenu();
          Menu submenu = menu.addSubMenu("Saved Albums");

         if(album!=null && album.getItems().size()>0){
             for (int i=0;i<album.getItems().size();i++)
                 submenu.add(album.getItems().get(i).getAlbum().getName());
          }

          navView.invalidate();
      }
    }



    @Override
    public void showLoader() {bar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideLoader() {
        bar.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showAlbumDetails(){
        Bundle bundle = new Bundle();
        bundle.putString("ALBUM_ID", albumId);
        bundle.putString("ALBUM_IMAGE",albumDetail.getImages().get(0).getUrl());
        TrackFragment trackFragment = new TrackFragment();
        trackFragment.setArguments(bundle);
        BaseFragment
                .addToBackStack(getFragmentsManager(), trackFragment, false);
    }

    @Override
    public void showDrawerToggle(boolean showDrawerToggle) {
        if(!showDrawerToggle) {
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }else{
            mDrawerToggle.setDrawerIndicatorEnabled(true);

        }

        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentsManager().popBackStack();
        } else {
            super.onBackPressed();

        }
    }
}
