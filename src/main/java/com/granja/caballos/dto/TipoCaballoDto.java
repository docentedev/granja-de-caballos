package com.granja.caballos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoCaballoDto(
        Long id,
        @NotBlank(message = "El nombre del tipo de caballo no puede estar vacío") String nombre,
        @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres") String descripcion) {

}
