package com.example.listadetarefas.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.Model.ModelTarefa;
import com.example.listadetarefas.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    List<ModelTarefa> stringList;
    ModelTarefa modelTarefaStr;
    View view;
    MyViewHolder viewHolder;

    public Adapter(List<ModelTarefa> List) {

        this.stringList = List;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list ,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.viewHolder = holder;

        modelTarefaStr = stringList.get(position);

        holder.strTarefa.setText( modelTarefaStr.getStrNome() );
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public List<ModelTarefa> getListAdapter(){
        return stringList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void dellItemList(int position){
        stringList.remove(position);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView strTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            strTarefa = itemView.findViewById(R.id.textViewlist);
        }
    }
}
