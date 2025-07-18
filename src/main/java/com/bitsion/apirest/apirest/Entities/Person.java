package com.bitsion.apirest.apirest.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String documentation;
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
    @Max(value = 100, message = "La edad debe ser menor o igual a 100")
    private int age;

    @NotBlank(message = "El género es obligatorio")
    private String gender;

    @NotNull(message = "El estado es obligatorio")
    private Boolean isActive = true;

    @NotNull(message = "El campo 'maneja' es obligatorio")
    private Boolean drives;

    @NotNull(message = "El campo 'usa lentes' es obligatorio")
    private Boolean wearsGlasses;

    @NotNull(message = "El campo 'diabético' es obligatorio")
    private Boolean isDiabetic;

    private String otherDisease;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    } 

    public String getDocumentation() {
        return documentation;
    }
    
    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getDrives() {
        return drives;
    }
    
    public void setDrives(Boolean drives) {
        this.drives = drives;
    }

    public Boolean getWearsGlasses() {
        return wearsGlasses;
    }
    
    public void setWearsGlasses(Boolean wearsGlasses) {
        this.wearsGlasses = wearsGlasses;
    }

    public Boolean getIsDiabetic() {
        return isDiabetic;
    }
    
    public void setIsDiabetic(Boolean isDiabetic) {
        this.isDiabetic = isDiabetic;
    }

    public String getOtherDisease() {
        return otherDisease;
    }
    
    public void setOtherDisease(String otherDisease) {
        this.otherDisease = otherDisease;
    }
}