package com.granja.caballos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granja.caballos.dto.TipoCaballoDto;
import com.granja.caballos.exception.NotFoundException;
import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.repository.TipoCaballoRepository;

@Service
public class TipoCaballoServiceImpl implements TipoCaballoService {

    private final TipoCaballoRepository repository;

    public TipoCaballoServiceImpl(TipoCaballoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoCaballoDto> findAll() {
        return repository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public TipoCaballoDto findById(Long id) {
        TipoCaballo tipo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("TipoCaballo no encontrado: " + id));
        return entityToDto(tipo);
    }

    @Override
    public TipoCaballoDto create(TipoCaballoDto req) {
        return entityToDto(repository.save(dtoToEntity(req)));
    }

    @Override
    public TipoCaballoDto update(Long id, TipoCaballoDto req) {
        TipoCaballo existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("TipoCaballo no encontrado: " + id));
        TipoCaballo updated = dtoToEntity(req);
        updated.setId(existing.getId());
        return entityToDto(repository.save(updated));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("TipoCaballo no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private TipoCaballoDto entityToDto(TipoCaballo entity) {
        return new TipoCaballoDto(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    private TipoCaballo dtoToEntity(TipoCaballoDto dto) {
        return new TipoCaballo(dto.nombre(), dto.descripcion());
    }
}
