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
 * @author vinic
 */
@Entity
@Table(name ="tarefa_status")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class TarefaStatus implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Tarefa tarefa;
    @ManyToOne
    private Status status;
    
    private String dataInicioFormat;
    private String dataFimFormat;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;

    public TarefaStatus() {
    }

    public TarefaStatus(Long id, Tarefa tarefa, Status status, String dataInicioFormat, String dataFimFormat, Date dataInicio, Date dataFim) {
        this.id = id;
        this.tarefa = tarefa;
        this.status = status;
        this.dataInicioFormat = dataInicioFormat;
        this.dataFimFormat = dataFimFormat;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public String getDataInicioFormat() {
        return dataInicioFormat;
    }

    public void setDataInicioFormat(String dataInicioFormat) {
        this.dataInicioFormat = dataInicioFormat;
    }

    public String getDataFimFormat() {
        return dataFimFormat;
    }

    public void setDataFimFormat(String dataFimFormat) {
        this.dataFimFormat = dataFimFormat;
    }
}
