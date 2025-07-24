package com.bitsion.apirest.apirest.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String fullName;

    @NotBlank(message = "La documentación es obligatoria")
    private String documentation;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    @Max(value = 100, message = "La edad debe ser menor o igual a 100")
    private Integer age;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotNull(message = "El estado es obligatorio")
    private Boolean isActive;

    @NotNull(message = "El campo 'maneja' es obligatorio")
    private Boolean drives;

    @NotNull(message = "El campo 'usa lentes' es obligatorio")
    private Boolean wearsGlasses;

    @NotNull(message = "El campo 'diabético' es obligatorio")
    private Boolean isDiabetic;

    private String otherDisease;
}
