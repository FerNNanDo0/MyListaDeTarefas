package com.example.listadetarefas.SQlite;

import com.example.listadetarefas.Model.ModelTarefa;

import java.util.List;

public interface ITarefaDAO {

    public boolean Salvar(ModelTarefa tarefa);

    public boolean Atulizar(ModelTarefa tarefa);

    public boolean Deletar(ModelTarefa tarefa);

    public List<ModelTarefa> ListarTarefa();

}
