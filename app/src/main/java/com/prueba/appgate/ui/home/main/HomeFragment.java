package com.prueba.appgate.ui.home.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.prueba.appgate.R;
import com.prueba.appgate.databinding.HomeFragmentBinding;
import com.prueba.appgate.models.TimezoneJsonModel;
import com.prueba.appgate.ui.ActivityListener;
import com.prueba.appgate.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends DaggerFragment implements ActivityListener {

    private HomeViewModel homeViewModel;


    @Inject
    ViewModelProviderFactory providerFactory;

    private HomeFragmentBinding binding;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setClase(this);
        binding.setModel(homeViewModel);

        binding.tilUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tilUsername.setError("");
            }
        });

        binding.tilPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tilPassword.setError("");
            }
        });
        binding.txtSigUp.setOnClickListener(v -> Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home).navigate(
                R.id.action_homeFragment_to_registroFragment
        ));

    }

    @SuppressLint("MissingPermission")
    public void validateUser() {
        if (TextUtils.isEmpty(binding.tilUsername.getEditText().getText())) {
            binding.tilUsername.setError(getString(R.string.error_user));
            return;
        }

        if (TextUtils.isEmpty(binding.tilPassword.getEditText().getText())) {
            binding.tilPassword.setError(getString(R.string.error_pass));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.tilUsername.getEditText().getText()).matches()) {
            binding.tilUsername.setError(getString(R.string.error_email));
            return;
        }

        LocationManager lm = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = lm.getBestProvider(criteria, false);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        Location location = lm.getLastKnownLocation(provider);
        if (location != null) {
            homeViewModel.longitude = location.getLongitude();
            homeViewModel.latitude = location.getLatitude();
        }
        ProgressDialog progress = ProgressDialog.show(getContext(), "Cargando", "Espere un momento...");
        Disposable data_for_server = homeViewModel.dateForServer()
                .subscribeOn(Schedulers.newThread())
                .onErrorReturn(throwable -> new TimezoneJsonModel())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(timezoneJsonModel -> {
                    progress.dismiss();
                    if (timezoneJsonModel.getTime() != null) {
                        homeViewModel.intentosModel.setFecha(timezoneJsonModel.getTime());

                        if (homeViewModel.validateUser()) {
                            homeViewModel.intentosModel.setResultado(true);
                            homeViewModel.saveIntentoRealm();
                            changeView();
                        } else {
                            homeViewModel.intentosModel.setResultado(false);
                            homeViewModel.saveIntentoRealm();
                            Toast.makeText(getActivity(), "Verifique usuario o contraseña", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Verifique conexion", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(data_for_server);
    }


    private void changeView() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home).navigate(
                HomeFragmentDirections.actionHomeFragmentToIntentosFragment(homeViewModel.loginModel.username)
        );
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            homeViewModel.longitude = location.getLongitude();
            homeViewModel.latitude = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    };


    @Override
    public void onBackPressed() {
        Toast.makeText(getActivity(), "Back", Toast.LENGTH_SHORT).show();
        getActivity().finish();

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
