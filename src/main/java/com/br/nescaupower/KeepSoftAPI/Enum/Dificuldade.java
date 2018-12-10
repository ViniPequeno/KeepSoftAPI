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
public enum Dificuldade {
    FACIL("Fácil"), MEDIO("Médio"), DIFICL("Difícil"), MUITO_DIFICIL("Muito Difícil");

    private String dificuldade;

    Dificuldade(final String text) {
        this.dificuldade = text;
    }

    @Override
    public String toString(){
        return this.dificuldade;
    }

    public Dificuldade[] toArray(){
        return Dificuldade.values();
    }
}
