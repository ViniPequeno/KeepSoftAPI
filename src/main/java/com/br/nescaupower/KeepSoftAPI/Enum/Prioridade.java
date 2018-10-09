/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Enum;

/**
 *
 * @author developer
 */
public enum Prioridade {
    BAIXA("Baixa"), MEDIA("Média"), ALTA("Alta"), MUITO_ALTA("Muito alta");

    private String prioridade;

    Prioridade(final String text) {
        this.prioridade = text;
    }

    @Override
    public String toString(){
        return this.prioridade;
    }

    public Prioridade[] toArray(){
        return Prioridade.values();
    }
}
