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
public enum Perfil {
     SCRUM_MASTER("Scrum Master"), PRODUCT_OWNER("Product Owner"), TEAM("Scrum Team");

    private String perfil;

    Perfil(final String text) {
        this.perfil = text;
    }

    @Override
    public String toString(){
        return this.perfil;
    }

    public Perfil[] toArray(){
        return Perfil.values();
    }
}
