package com.granja.caballos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granja.caballos.model.Caballo;

/**
 * Repositorio para la entidad Caballo, proporciona métodos CRUD básicos
 * mediante la extensión de JpaRepository.
 * No se requieren métodos adicionales por ahora, pero se pueden agregar
 * consultas personalizadas si es necesario.
 */
public interface CaballoRepository extends JpaRepository<Caballo, Long> {

}
