package com.granja.caballos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granja.caballos.dto.CaballoDto;
import com.granja.caballos.dto.TipoCaballoDto;
import com.granja.caballos.exception.NotFoundException;
import com.granja.caballos.model.Caballo;
import com.granja.caballos.model.TipoCaballo;
import com.granja.caballos.repository.CaballoRepository;

@Service
public class CaballoServiceImpl implements CaballoService {

    private final CaballoRepository repo;
    private final TipoCaballoService tipoService;

    public CaballoServiceImpl(CaballoRepository repo, TipoCaballoService tipoService) {
        this.repo = repo;
        this.tipoService = tipoService;
    }

    @Override
    public List<CaballoDto> findAll() {
        return repo.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public CaballoDto findById(Long id) throws NotFoundException {
        Caballo caballo = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Caballo no encontrado: " + id));
        return entityToDto(caballo);
    }

    @Override
    public CaballoDto create(CaballoDto req) {
        return entityToDto(repo.save(dtoToEntity(req)));
    }

    @Override
    public CaballoDto update(Long id, CaballoDto req) throws NotFoundException {
        Caballo existing = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Caballo no encontrado: " + id));
        Caballo updated = dtoToEntity(req);
        updated.setId(existing.getId());
        return entityToDto(repo.save(updated));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Caballo no encontrado: " + id);
        }
        repo.deleteById(id);
    }

    private CaballoDto entityToDto(Caballo entity) {
        return new CaballoDto(entity.getId(), entity.getNombre(), entity.getColor(), entity.getEdad(),
                tipoEntityToDto(entity.getTipo()));
    }

    private TipoCaballoDto tipoEntityToDto(TipoCaballo tipo) {
        return new TipoCaballoDto(tipo.getId(), tipo.getNombre(), tipo.getDescripcion());
    }

    private Caballo dtoToEntity(CaballoDto dto) {
        TipoCaballoDto tipo = tipoService.findById(dto.tipo().id());
        return new Caballo(dto.nombre(), dto.color(), dto.edad(), dtoToTipoEntity(tipo));
    }

    private TipoCaballo dtoToTipoEntity(TipoCaballoDto dto) {
        TipoCaballo tipo = new TipoCaballo();
        tipo.setId(dto.id());
        tipo.setNombre(dto.nombre());
        tipo.setDescripcion(dto.descripcion());
        return tipo;
    }
}
