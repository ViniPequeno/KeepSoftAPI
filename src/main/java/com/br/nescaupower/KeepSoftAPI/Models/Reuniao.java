/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author vinic
 */
@Entity
@Table(name = "reuniao")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Reuniao implements Serializable {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "reuniao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReuniaoUsuario> reuniaoUsuarios;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String resumo;
    private String assunto;
    private String local;
    
    private String dataInicioFormat;
    private String horaInicioFormat;
    private String horaFimFormat;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;

    @ManyToOne(optional = false)
    private Projeto projeto;

    private boolean realizada = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDataInicioFormat() {
        return dataInicioFormat;
    }

    public void setDataInicioFormat(String dataInicioFormat) {
        this.dataInicioFormat = dataInicioFormat;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }



    public List<ReuniaoUsuario> getReuniaoUsuarios() {
        return reuniaoUsuarios;
    }

    public void setReuniaoUsuarios(List<ReuniaoUsuario> reuniaoUsuarios) {
        this.reuniaoUsuarios = reuniaoUsuarios;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getHoraInicioFormat() {
        return horaInicioFormat;
    }

    public void setHoraInicioFormat(String horaInicioFormat) {
        this.horaInicioFormat = horaInicioFormat;
    }

    public String getHoraFimFormat() {
        return horaFimFormat;
    }

    public void setHoraFimFormat(String horaFimFormat) {
        this.horaFimFormat = horaFimFormat;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    
}
