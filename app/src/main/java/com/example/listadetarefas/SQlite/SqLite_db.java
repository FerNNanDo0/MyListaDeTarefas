package com.example.listadetarefas.SQlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqLite_db extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NAME_db = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    // criar tabela se nao existir
    String sqlCreate = "CREATE TABLE IF NOT EXISTS "+
            TABELA_TAREFAS+
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " nome TEXT NOT NULL ) ";

    // apagar tabela
    String sqlDEL = " DROP TABLE IF EXISTS  "+
            TABELA_TAREFAS+ " ;";

    // atualizar tabela
    String sqlUPdate = "ALTER TABLE "+
            TABELA_TAREFAS+
            " ADD COLUMN status VARCHAR(1)";


    public SqLite_db(@Nullable Context context) {
        super(context, NAME_db, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL( sqlCreate );
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        try {

            db.execSQL( sqlDEL );
            onCreate(db);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
