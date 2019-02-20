/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.service;

import com.tincom.ocas.entities.Categorie;
import com.tincom.ocas.service.dto.CategorieDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author roland
 */
public interface ICategorieService {

    public Page<Categorie> getListCategorie(Pageable pageable);

    public Optional<CategorieDto> update(CategorieDto categorieDto);

    public Categorie save(CategorieDto categorieDto);


    public void delete(Long id);

}
