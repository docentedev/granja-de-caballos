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

import com.granja.caballos.dto.TipoCaballoDto;
import com.granja.caballos.service.TipoCaballoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tipos")
public class TipoCaballoController {

    private final TipoCaballoService service;

    public TipoCaballoController(TipoCaballoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoCaballoDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCaballoDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoCaballoDto> create(@Valid @RequestBody TipoCaballoDto req) {
        TipoCaballoDto created = service.create(req);
        return ResponseEntity.created(URI.create("/api/tipos/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCaballoDto> update(@PathVariable Long id, @Valid @RequestBody TipoCaballoDto req) {
        TipoCaballoDto updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
