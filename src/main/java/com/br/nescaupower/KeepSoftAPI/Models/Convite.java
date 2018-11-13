/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Models;

import com.br.nescaupower.KeepSoftAPI.Enum.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author developer
 */
@Entity
@Table(name = "convite")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Convite implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private com.br.nescaupower.KeepSoftAPI.Enum.Perfil funcao;
    @ManyToOne
    private Usuario remetenteId;
    @ManyToOne
    private Usuario destinatarioId;
    @ManyToOne
    private Projeto projeto;
    
    private String dataEnvioFormat;
    private boolean visto = false;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perfil getFuncao() {
        return funcao;
    }

    public void setFuncao(Perfil funcao) {
        this.funcao = funcao;
    }

    public Usuario getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Usuario remetenteId) {
        this.remetenteId = remetenteId;
    }

    public Usuario getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Usuario destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getDataEnvioFormat() {
        return dataEnvioFormat;
    }

    public void setDataEnvioFormat(String dataEnvioFormat) {
        this.dataEnvioFormat = dataEnvioFormat;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }
    
    
}
