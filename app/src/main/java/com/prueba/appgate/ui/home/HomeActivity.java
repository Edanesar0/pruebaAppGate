package com.prueba.appgate.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.prueba.appgate.BaseApplication;
import com.prueba.appgate.R;
import com.prueba.appgate.models.LoginModel;
import com.prueba.appgate.ui.ActivityListener;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.DaggerAppCompatActivity;
import io.realm.Realm;
import io.realm.RealmResults;

public class HomeActivity extends DaggerAppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_FINE = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        LoginModel login = new LoginModel();
        login.setUsername("1@1.1");
        login.setPassword("12345");
        realm.executeTransaction(realm1 -> {
                    RealmResults<LoginModel> query = realm1.where(LoginModel.class).equalTo("username", "1@1.1").findAll();
                    if (query.size() == 0)
                        realm1.copyToRealmOrUpdate(login);
                }
        );

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_FINE) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Todos los permisos son necesarios", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    private ActivityListener getCurrentFragment() {
        final Fragment fm = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_home);
        Fragment currentFragment = fm.getChildFragmentManager().getFragments().get(0);
        if (currentFragment instanceof ActivityListener) {
            return (ActivityListener) currentFragment;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getCurrentFragment().onBackPressed();
    }

}
