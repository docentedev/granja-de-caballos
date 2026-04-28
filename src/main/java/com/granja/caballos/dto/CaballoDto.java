package com.granja.caballos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CaballoDto(
                Long id,

                @NotBlank(message = "El nombre del caballo no puede estar vacío") String nombre,

                @NotBlank(message = "El color del caballo no puede estar vacío") String color,

                @NotNull(message = "La edad es obligatoria") @Positive(message = "La edad debe ser un número positivo") Integer edad,

                @Valid @NotNull(message = "El tipo de caballo es obligatorio") TipoCaballoDto tipo) {
}
