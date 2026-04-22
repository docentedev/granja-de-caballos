package com.granja.caballos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granja.caballos.exception.ResourceNotFoundException;
import com.granja.caballos.model.Caballo;
import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.repository.CaballoRepository;
import com.granja.caballos.repository.TipoCaballoRepository;

@Service
public class CaballoServiceImpl implements CaballoService {

    private final CaballoRepository repo;
    private final TipoCaballoRepository tipoRepo;

    public CaballoServiceImpl(CaballoRepository repo, TipoCaballoRepository tipoRepo) {
        this.repo = repo;
        this.tipoRepo = tipoRepo;
    }

    @Override
    public List<Caballo> findAll() {
        return repo.findAll();
    }

    @Override
    public Caballo findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Caballo no encontrado: " + id));
    }

    @Override
    public Caballo create(Caballo caballo) {
        // ensure tipo exists
        TipoCaballo tipo = tipoRepo.findById(caballo.getTipo().getId())
                .orElseThrow(() -> new ResourceNotFoundException("TipoCaballo no encontrado: " + caballo.getTipo().getId()));
        caballo.setTipo(tipo);
        return repo.save(caballo);
    }

    @Override
    public Caballo update(Long id, Caballo caballo) {
        Caballo existing = findById(id);
        existing.setNombre(caballo.getNombre());
        existing.setEdad(caballo.getEdad());
        if (caballo.getTipo() != null) {
            TipoCaballo tipo = tipoRepo.findById(caballo.getTipo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("TipoCaballo no encontrado: " + caballo.getTipo().getId()));
            existing.setTipo(tipo);
        }
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
