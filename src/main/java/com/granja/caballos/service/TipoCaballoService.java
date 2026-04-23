package com.granja.caballos.service;

import java.util.List;

import com.granja.caballos.dto.TipoCaballoDto;

public interface TipoCaballoService {
    List<TipoCaballoDto> findAll();

    TipoCaballoDto findById(Long id);

    TipoCaballoDto create(TipoCaballoDto req);

    TipoCaballoDto update(Long id, TipoCaballoDto req);

    void delete(Long id);
}
