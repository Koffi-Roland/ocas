/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.service.dto;

import com.tincom.ocas.entities.Categorie;
import java.sql.Blob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author roland
 */
public class CategorieDto {

    private Long id;
    @Size(max = 255)
    private String libelle;
    @NotNull
    private String description;

    public CategorieDto() {
    }

    public CategorieDto(Long id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public CategorieDto(Categorie categorie) {
        this.id = categorie.getId();
        this.libelle = categorie.getLibelle();
        this.description = categorie.getDescription();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
