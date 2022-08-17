package com.example.listadetarefas.Model;

import java.io.Serializable;

public class ModelTarefa implements Serializable {
    private String strNome;
    private Long id;

    public ModelTarefa(){}

    public ModelTarefa(String str) {
        this.strNome = str;
    }

    public String getStrNome() {
        return strNome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
