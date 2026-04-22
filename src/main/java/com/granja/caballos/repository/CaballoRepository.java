package com.granja.caballos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granja.caballos.model.Caballo;

public interface CaballoRepository extends JpaRepository<Caballo, Long> {

}
