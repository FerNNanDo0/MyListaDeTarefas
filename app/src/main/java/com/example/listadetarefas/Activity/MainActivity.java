package com.example.listadetarefas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadetarefas.Adapter.Adapter;
import com.example.listadetarefas.Model.ModelTarefa;
import com.example.listadetarefas.R;
import com.example.listadetarefas.Adapter.RecyclerItemClickListener;
import com.example.listadetarefas.SQlite.TarefaDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;

    Intent intent;
    AlertDialog.Builder dialog;

    SQLiteDatabase sqLiteDatabase;
    List<ModelTarefa> listaDeTarefa;
    ModelTarefa tarefa;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);

        // criar banco de dados SQLite
        sqLiteDatabase = openOrCreateDatabase("app_tarefas", MODE_PRIVATE, null);

        // Configurar RecyclerView
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        dialog = new AlertDialog.Builder(this);


        fab.setOnClickListener(view -> {
            intent = new Intent(this, MainActivity_two.class);
            startActivity(intent);
        });


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        // Recuperar tarefa para editar
                        ModelTarefa tarefaSelecionada = listaDeTarefa.get(position);

                        // eviar tarefa para activity de edição
                        Intent intent = new Intent(getApplicationContext(), MainActivity_two.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        tarefa = listaDeTarefa.get(position);

                        dialog.setTitle(" Excluir tarefa ")
                                .setMessage(" Deseja excluir a tarefa " + tarefa.getStrNome() + "? ")


                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        adapter.dellItemList(position);
                                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                                        if (tarefaDAO.Deletar(tarefa)) {

                                            Toast.makeText(getApplicationContext(), "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("cancel", null);

                        AlertDialog alert = dialog.create();
                        alert.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));


    }

    @Override
    protected void onStart() {
        listaDeTarefa = new TarefaDAO(this).ListarTarefa();

        // config adapter
        adapter = new Adapter(listaDeTarefa);
        recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_conf, menu);

        return true; //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_conf) {

        }

        return super.onOptionsItemSelected(item);
    }

}