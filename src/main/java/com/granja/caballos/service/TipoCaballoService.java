package com.granja.caballos.service;

import java.util.List;

import com.granja.caballos.model.TipoCaballo;

public interface TipoCaballoService {
    List<TipoCaballo> findAll();
    TipoCaballo findById(Long id);
    TipoCaballo create(TipoCaballo tipo);
    TipoCaballo update(Long id, TipoCaballo tipo);
    void delete(Long id);
}
