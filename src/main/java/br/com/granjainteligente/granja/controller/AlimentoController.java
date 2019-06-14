/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.granjainteligente.granja.controller;

import br.com.granjainteligente.granja.Exception.ResourceNotFoundException;
import br.com.granjainteligente.granja.Repository.AlimentoRepository;
import br.com.granjainteligente.granja.model.Alimento;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author felip
 */
@RestController
@RequestMapping("/api")
public class AlimentoController {

    @Autowired
    AlimentoRepository alimentoService;

    @GetMapping("/Alimento")
    public List<Alimento> getAllAlimento() {
        return alimentoService.findAll();
    }

    @PostMapping("/Alimento")
    public Alimento createAlimento(@Valid @RequestBody Alimento alimento) {
        return alimentoService.save(alimento);
    }

    @PutMapping("/Alimento/{id}")
    public Alimento updateAlimento(@PathVariable(value = "id") long alimentoId, @Valid @RequestBody Alimento model) {
        Alimento sensor = alimentoService.findById(alimentoId).orElseThrow(() -> new ResourceNotFoundException("Alimento", "id", alimentoId));
        sensor.setAuto(model.isAuto());
        sensor.setData(model.getData());
        sensor.setDescricao(model.getDescricao());
        sensor.setEstado(model.isEstado());
        sensor.setNivel(model.getNivel());
        sensor.setNivelSet(model.getNivelSet());
        
        Alimento updateSensor = alimentoService.save(sensor);

        return updateSensor;

    }

}
