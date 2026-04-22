package com.granja.caballos.service;

import java.util.List;

import com.granja.caballos.model.Caballo;

public interface CaballoService {
    List<Caballo> findAll();
    Caballo findById(Long id);
    Caballo create(Caballo caballo);
    Caballo update(Long id, Caballo caballo);
    void delete(Long id);
}
