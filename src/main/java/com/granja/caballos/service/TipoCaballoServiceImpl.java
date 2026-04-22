package com.granja.caballos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granja.caballos.exception.ResourceNotFoundException;
import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.repository.TipoCaballoRepository;

@Service
public class TipoCaballoServiceImpl implements TipoCaballoService {

    private final TipoCaballoRepository repository;

    public TipoCaballoServiceImpl(TipoCaballoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoCaballo> findAll() {
        return repository.findAll();
    }

    @Override
    public TipoCaballo findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoCaballo no encontrado: " + id));
    }

    @Override
    public TipoCaballo create(TipoCaballo tipo) {
        return repository.save(tipo);
    }

    @Override
    public TipoCaballo update(Long id, TipoCaballo tipo) {
        TipoCaballo existing = findById(id);
        existing.setNombre(tipo.getNombre());
        existing.setDescripcion(tipo.getDescripcion());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
