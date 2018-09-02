/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Models.Auxiliares;

/**
 *
 * @author vinic
 */
public class AlterarSenha {
    private Long id;
    private String senha;

    public AlterarSenha() {
    }

    public AlterarSenha(Long id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
