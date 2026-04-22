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

import com.granja.caballos.dto.CaballoRequest;
import com.granja.caballos.model.Caballo;
import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.service.CaballoService;
import com.granja.caballos.service.TipoCaballoService;

@RestController
@RequestMapping("/api/caballos")
public class CaballoController {

    private final CaballoService service;
    private final TipoCaballoService tipoService;

    public CaballoController(CaballoService service, TipoCaballoService tipoService) {
        this.service = service;
        this.tipoService = tipoService;
    }

    @GetMapping
    public List<Caballo> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caballo> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Caballo> create(@RequestBody CaballoRequest req) {
        TipoCaballo tipo = tipoService.findById(req.getTipoId());
        Caballo c = new Caballo(req.getNombre(), req.getEdad(), tipo);
        Caballo created = service.create(c);
        return ResponseEntity.created(URI.create("/api/caballos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caballo> update(@PathVariable Long id, @RequestBody CaballoRequest req) {
        TipoCaballo tipo = null;
        if (req.getTipoId() != null) tipo = tipoService.findById(req.getTipoId());
        Caballo c = new Caballo(req.getNombre(), req.getEdad(), tipo);
        Caballo updated = service.update(id, c);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
