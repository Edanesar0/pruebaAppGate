package com.prueba.appgate.ui.home.intentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prueba.appgate.BaseApplication;
import com.prueba.appgate.adapters.MyAdapter;
import com.prueba.appgate.databinding.HistorialFragmentBinding;
import com.prueba.appgate.models.LoginModel;
import com.prueba.appgate.ui.ActivityListener;
import com.prueba.appgate.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.realm.Realm;
import io.realm.RealmResults;

public class IntentosFragment extends DaggerFragment implements ActivityListener {

    private IntentosViewModel intentosViewModel;


    @Inject
    ViewModelProviderFactory providerFactory;

    private HistorialFragmentBinding binding;
    String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HistorialFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        intentosViewModel = new ViewModelProvider(this, providerFactory).get(IntentosViewModel.class);
        binding.setLifecycleOwner(this);
        binding.rcHistorial.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcHistorial.setLayoutManager(layoutManager);
        IntentosFragmentArgs params = IntentosFragmentArgs.fromBundle(getArguments());
        username = params.getUsername();

        Realm realm = Realm.getInstance(BaseApplication.realmConfiguration);
        realm.executeTransaction(realm1 -> {
            RealmResults<LoginModel> query = realm1.where(LoginModel.class).equalTo("username", username).findAll();
            if (query.size() > 0) {
                intentosViewModel.loginModel = query.first();
            }
        });
        MyAdapter mAdapter = new MyAdapter(intentosViewModel.loginModel);
        binding.rcHistorial.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
       /* Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home).navigate(
                R.id.action_intentosFragment_to_homeFragment
        );*/
    }
}
