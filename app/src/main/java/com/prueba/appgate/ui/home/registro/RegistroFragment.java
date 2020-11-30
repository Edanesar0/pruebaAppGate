package com.prueba.appgate.ui.home.registro;

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
import com.prueba.appgate.databinding.RegistroFragmentBinding;
import com.prueba.appgate.ui.ActivityListener;
import com.prueba.appgate.ui.home.main.HomeViewModel;
import com.prueba.appgate.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RegistroFragment extends DaggerFragment implements ActivityListener {


    private RegistroViewModel registroViewModel;


    @Inject
    ViewModelProviderFactory providerFactory;

    private RegistroFragmentBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RegistroFragmentBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        registroViewModel = new ViewModelProvider(this, providerFactory).get(RegistroViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setClase(this);
        binding.setModel(registroViewModel);

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

        binding.tilPasswordConfirm.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tilPasswordConfirm.setError("");
            }
        });


    }

    public void validateUser() {
        if (TextUtils.isEmpty(binding.tilUsername.getEditText().getText())) {
            binding.tilUsername.setError(getString(R.string.error_user));
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.tilUsername.getEditText().getText()).matches()) {
            binding.tilUsername.setError(getString(R.string.error_email));
            return;
        }

        if (TextUtils.isEmpty(binding.tilPassword.getEditText().getText())) {
            binding.tilPassword.setError(getString(R.string.error_pass));
            return;
        }
        if (TextUtils.isEmpty(binding.tilPasswordConfirm.getEditText().getText())) {
            binding.tilPasswordConfirm.setError(getString(R.string.error_pass_confrim));
            return;
        }
        if (binding.tilPassword.getEditText().getText().length() < 8) {
            binding.tilPassword.setError(getString(R.string.error_pass_leng));
            return;
        }
        if (binding.tilPasswordConfirm.getEditText().getText().length() < 8) {
            binding.tilPasswordConfirm.setError(getString(R.string.error_pass_con_leng));
            return;
        }
        if (!binding.tilPassword.getEditText().getText().toString().equals(binding.tilPasswordConfirm.getEditText().getText().toString())) {
            binding.tilPasswordConfirm.setError(getString(R.string.error_pass_confrim_equals));
            return;
        }

        if (registroViewModel.validateUser()) {
            binding.tilUsername.setError(getString(R.string.error_user_register));
            Toast.makeText(getActivity(), R.string.error_user_register, Toast.LENGTH_SHORT).show();

        } else {
            registroViewModel.saveLoginRealm();
            Toast.makeText(getActivity(), R.string.user_register, Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home).navigate(
                R.id.action_registroFragment_to_homeFragment
        );
    }
}
