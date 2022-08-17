package com.example.listadetarefas.SQlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.listadetarefas.Model.ModelTarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    SqLite_db db;
    List<ModelTarefa> lista_tarefa = new ArrayList<>();

    private SQLiteDatabase add_Dados;
    private SQLiteDatabase leia_Dados;
    Context context;

    public TarefaDAO(Context context){ this.context = context;
        db = new SqLite_db( context );

        add_Dados = db.getWritableDatabase();
        leia_Dados = db.getReadableDatabase();
    }

    @Override
    public boolean Salvar(ModelTarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put( "nome", tarefa.getStrNome());

        try {
            add_Dados.insert( SqLite_db.TABELA_TAREFAS, null, cv  );

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean Atulizar(ModelTarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put( "nome", tarefa.getStrNome() );

        try {
            String[] args = { tarefa.getId().toString() };

            add_Dados.update( SqLite_db.TABELA_TAREFAS, cv, "id=?", args );

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Deletar(ModelTarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put( "nome", tarefa.getStrNome() );

        try {
            String[] args = { tarefa.getId().toString() };

            add_Dados.delete( SqLite_db.TABELA_TAREFAS, "id=?", args );
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint("Recycle")
    @Override
    public List<ModelTarefa> ListarTarefa() {
        String nomeTabela = SqLite_db.TABELA_TAREFAS;

        String sqlBuscarDados = "SELECT * FROM "+ nomeTabela + " ;";

        Cursor c = leia_Dados.rawQuery( sqlBuscarDados, null );

        //c.moveToFirst();
        while (c.moveToNext()){

            @SuppressLint("Range")
            Long id = c.getLong( c.getColumnIndex("id" ) );

            @SuppressLint("Range")
            String nomeTarefa = c.getString( c.getColumnIndex( "nome" ) );

            ModelTarefa Tarefa = new ModelTarefa( nomeTarefa);
            Tarefa.setId( id );

            lista_tarefa.add( Tarefa );

        }

        return this.lista_tarefa;
    }
}
