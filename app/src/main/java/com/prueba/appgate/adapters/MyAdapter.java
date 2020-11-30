package com.prueba.appgate.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prueba.appgate.R;
import com.prueba.appgate.models.LoginModel;

import org.jetbrains.annotations.NotNull;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private LoginModel mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtFecha;
        public TextView txtResultado;
        public TextView txtUser;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtResultado = itemView.findViewById(R.id.txtResultado);
            txtUser = itemView.findViewById(R.id.txtUser);
        }
    }


    public MyAdapter(LoginModel myDataset) {
        mDataset = myDataset;
    }


    @NotNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historal_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtUser.setText(mDataset.getUsername());
        holder.txtFecha.setText(mDataset.intentos.get(position).getFecha());
        holder.txtResultado.setText(mDataset.intentos.get(position).isResultado()?"Exitoso":"Denegado");
    }

    @Override
    public int getItemCount() {
        return mDataset.getIntentos().size();
    }
}
