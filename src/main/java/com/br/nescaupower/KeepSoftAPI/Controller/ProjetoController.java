/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.nescaupower.KeepSoftAPI.Controller;

import com.br.nescaupower.KeepSoftAPI.Exception.ResourceNotFoundException;
import com.br.nescaupower.KeepSoftAPI.Models.Auxiliares.Porcentagem;
import com.br.nescaupower.KeepSoftAPI.Models.Perfil;
import com.br.nescaupower.KeepSoftAPI.Models.Projeto;
import com.br.nescaupower.KeepSoftAPI.Models.Status;
import com.br.nescaupower.KeepSoftAPI.Models.Tarefa;
import com.br.nescaupower.KeepSoftAPI.Models.TarefaStatus;
import com.br.nescaupower.KeepSoftAPI.Repository.ProjetoRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.nescaupower.KeepSoftAPI.Repository.PerfilRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.StatusRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.TarefaRepository;
import com.br.nescaupower.KeepSoftAPI.Repository.TarefaStatusRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author vinic
 */
@RestController
@RequestMapping("/api/projeto")
public class ProjetoController {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    PerfilRepository perfillRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaStatusRepository tarefaStatusRepository;

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Projeto getProjeto(@PathVariable(value = "id") Long projetoId) {
        return (Projeto) projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));
    }

    @GetMapping("/findByName/{name}")
    public Projeto findByName(@PathVariable(value = "name") String name) {
        return projetoRepository.findByName(name);
    }

    @GetMapping("/findByUserID/{id}")
    public List<Projeto> findByUserID(@PathVariable(value = "id") Long id) {
        return projetoRepository.findByUserID(id);
    }

    @GetMapping("/findByParticipantingUserID/{id}")
    public List<Projeto> findByParticipantingUserID(@PathVariable(value = "id") Long id) {
        return projetoRepository.findByParticipantingUserID(id);
    }

    @GetMapping("porcentagem/{id}")
    public Porcentagem findPorcentagemProjeto(@PathVariable(value = "id") Long id) {
        Porcentagem porcentagem = new Porcentagem();
        porcentagem.setIdProjeto(id);
        List<Tarefa> tarefas = tarefaRepository.findByProjeto(id);
        int tarefasFinalizadas = 0;
        for (Tarefa tarefa : tarefas) {
            System.out.println(""+tarefa.getId());
            TarefaStatus ts = tarefaStatusRepository.findCuurentStatusOfTarefa(tarefa.getId());
            if (ts != null) {
                Status s = ts.getStatus();
                if (s.getNome().equals("CONCLUIDA")) {
                    tarefasFinalizadas++;
                }
            } else {
                tarefasFinalizadas++;
            }
        }

        if (!tarefas.isEmpty()) {
            porcentagem.setPorcentagem((tarefasFinalizadas * 100) / tarefas.size());

            return porcentagem;
        } else {
            porcentagem.setPorcentagem(0);
            return porcentagem;
        }
    }

    @PostMapping
    public Projeto inserirProjeto(@Valid @RequestBody Projeto projeto) {

        Projeto projetoTeste = projetoRepository.isExist(projeto.getNome(),
                projeto.getUsuarioAdm().getId());
        if (projetoTeste == null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                projeto.setDataCriacao(formato.parse(projeto.getDataCriacaoFormat()));
                projeto.setDataPrevFinalizacao(formato.parse(projeto.getDataPrevFinalizacaoFormat()));
            } catch (ParseException ex) {
            }
            Projeto projetoSalvo = projetoRepository.save(projeto);

            StatusController statusController = new StatusController();
            String hexa;
            /////////////////////////////////////////////////////////////////////////////////////////////
            Status status = new Status();
            status.setProjeto(projetoSalvo);
            status.setNome("CRIADA");
            status.setDescricao("Uma tarefa que não foi iniciada ainda!");
            hexa = "44bbdd";
            status.setCor(Integer.parseInt(hexa, 16));
            statusRepository.save(status);
            ////////////////////////////////////////////////////////////////////////////////////////////
            Status status1 = new Status();
            status1.setProjeto(projetoSalvo);
            status1.setNome("DESENVOLMENTO");
            status1.setDescricao("Uma tarefa que está sendo desenvolvida!");
            hexa = "ddbb00";
            status1.setCor(Integer.parseInt(hexa, 16));
            statusRepository.save(status1);
            ////////////////////////////////////////////////////////////////////////////////////////////
            Status status2 = new Status();
            status2.setProjeto(projetoSalvo);
            status2.setNome("CONCLUIDA");
            status2.setDescricao("Uma tarefa concluída");
            hexa = "aadd00";
            status2.setCor(Integer.parseInt(hexa, 16));
            statusRepository.save(status2);
            ////////////////////////////////////////////////////////////////////////////////////////////
            return projetoSalvo;
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable(value = "id") Long projetoId,
            @Valid @RequestBody Projeto projetoUpdate) {

        Projeto projetoTeste = projetoRepository.isExist(projetoUpdate.getNome(),
                projetoUpdate.getUsuarioAdm().getId(), projetoUpdate.getCodigo());
        if (projetoTeste == null) {
            Projeto projeto = projetoRepository.findById(projetoId).
                    orElseThrow(() -> new ResourceNotFoundException("Projeto", "projeto", projetoId));

            projeto.setDataCriacaoFormat(projetoUpdate.getDataCriacaoFormat());
            projeto.setDataFinalizacaoFormat(projetoUpdate.getDataFinalizacaoFormat());
            projeto.setDataPrevFinalizacaoFormat(projetoUpdate.getDataPrevFinalizacaoFormat());
            projeto.setDataFinalizacao(projetoUpdate.getDataFinalizacao());
            projeto.setDataPrevFinalizacao(projetoUpdate.getDataPrevFinalizacao());
            projeto.setDescricao(projetoUpdate.getDescricao());
            projeto.setNome(projetoUpdate.getNome());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try {
                projeto.setDataCriacao(formato.parse(projetoUpdate.getDataCriacaoFormat()));
                projeto.setDataPrevFinalizacao(formato.parse(projetoUpdate.getDataPrevFinalizacaoFormat()));
            } catch (ParseException ex) {
            }

            return ResponseEntity.ok(projetoRepository.save(projeto));
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable(value = "id") Long projetoId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", "id", projetoId));

        projetoRepository.delete(projeto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/equipe")
    public ResponseEntity<Perfil> integrarEquipe(@Valid @RequestBody Perfil perfil) {
        return ResponseEntity.ok(perfillRepository.save(perfil));
    }
}
