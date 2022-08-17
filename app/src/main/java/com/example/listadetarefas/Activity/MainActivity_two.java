package com.example.listadetarefas.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.Model.ModelTarefa;
import com.example.listadetarefas.R;
import com.example.listadetarefas.SQlite.TarefaDAO;

public class MainActivity_two extends AppCompatActivity {

    EditText editText;
    String strText;
    Toast toast;
    ModelTarefa modelTarefa, tarefaAtual;
    TarefaDAO tarefaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        editText = findViewById(R.id.editText);
        toast = Toast.makeText(this,
                "Tarefa adicionada com sucesso!", Toast.LENGTH_SHORT);

        // recuperar a tarefa para editar
        tarefaAtual = (ModelTarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaAtual != null) {
            editText.setText(tarefaAtual.getStrNome());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirm, menu);

        return true; //super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        tarefaDAO = new TarefaDAO(this);

        if (item.getItemId() == R.id.menu_confirm) {
            if (tarefaAtual != null) {   // editar

                strText = editText.getText().toString();
                if (!strText.isEmpty()) {
                    modelTarefa = new ModelTarefa( strText );
                    modelTarefa.setId( tarefaAtual.getId() );

                    // atualizar o banco
                    if ( tarefaDAO.Atulizar( modelTarefa ) ){

                        finish();
                    }
                }

                } else {   // salvar

                    strText = editText.getText().toString();
                    if (!strText.isEmpty()) {
                        modelTarefa = new ModelTarefa(strText);

                        if (tarefaDAO.Salvar(modelTarefa)) {
                            toast.show();
                            finish();
                            return true;
                        }
                    }
                }

            }
            return super.onOptionsItemSelected(item);
        }

    }