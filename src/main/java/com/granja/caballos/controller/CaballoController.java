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

import com.granja.caballos.dto.CaballoDto;
import com.granja.caballos.service.CaballoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/caballos")
public class CaballoController {

    private final CaballoService service;

    public CaballoController(CaballoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CaballoDto> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaballoDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CaballoDto> create(@Valid @RequestBody CaballoDto req) {
        CaballoDto created = service.create(req);
        return ResponseEntity.created(URI.create("/api/caballos/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaballoDto> update(@PathVariable Long id, @Valid @RequestBody CaballoDto req) {
        CaballoDto updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
