package com.granja.caballos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.service.TipoCaballoService;

@RestController
@RequestMapping("/api/tipos")
public class TipoCaballoController {

    private final TipoCaballoService service;

    public TipoCaballoController(TipoCaballoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoCaballo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCaballo> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoCaballo> create(@RequestBody TipoCaballo tipo) {
        TipoCaballo created = service.create(tipo);
        return ResponseEntity.created(URI.create("/api/tipos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCaballo> update(@PathVariable Long id, @RequestBody TipoCaballo tipo) {
        TipoCaballo updated = service.update(id, tipo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
