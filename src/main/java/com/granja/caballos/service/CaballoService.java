package com.granja.caballos.service;

import java.util.List;

import com.granja.caballos.dto.CaballoDto;
import com.granja.caballos.exception.NotFoundException;

/**
 * Interfaz de servicio para la gestión de caballos. Define los métodos CRUD
 * que deben ser implementados por la clase CaballoServiceImpl. Esta interfaz
 * permite una separación clara entre la lógica de negocio y la capa de acceso a
 * 
 * @author Claudio
 * @version 1.0
 * @since 2024-06-01
 */
public interface CaballoService {
    List<CaballoDto> findAll();

    /**
     * Busca un caballo por su ID.
     *
     * @param id ID del caballo
     * @return DTO del caballo encontrado
     * @throws NotFoundException si el caballo no existe
     */
    CaballoDto findById(Long id) throws NotFoundException;

    CaballoDto create(CaballoDto caballo);

    CaballoDto update(Long id, CaballoDto caballo);

    void delete(Long id);
}
