package com.granja.caballos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granja.caballos.model.TipoCaballo;

public interface TipoCaballoRepository extends JpaRepository<TipoCaballo, Long> {
    Optional<TipoCaballo> findByNombre(String nombre);
}
