/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.service.impl;

import com.tincom.ocas.entities.Categorie;
import com.tincom.ocas.repository.ICategorieRepository;
import com.tincom.ocas.service.ICategorieService;
import com.tincom.ocas.service.dto.CategorieDto;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roland
 */
@Service
@Transactional
public class CategorieServiceImpl implements ICategorieService{
     private final Logger log = LoggerFactory.getLogger(ICategorieService.class);

    private final ICategorieRepository categorieRepository;

    public CategorieServiceImpl(ICategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Page<Categorie> getListCategorie(Pageable pageable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<CategorieDto> update(CategorieDto categorieDto) {
        
          return Optional.of(categorieRepository.getOne(categorieDto.getId()))
                .map(categorie -> {
                    categorie.setLibelle(categorieDto.getLibelle());
                    categorie.setDescription(categorieDto.getDescription());
                    log.debug("Update categorie : ");
                  
                    return categorie;
                })
                .map(CategorieDto::new);
    }

     @Override
    @Transactional
    public Categorie save(CategorieDto categorieDto) {
        Categorie categorie=new Categorie();
        categorie.setLibelle(categorieDto.getLibelle());
        categorie.setDescription(categorieDto.getDescription());
        Categorie _categorie= categorieRepository.save(categorie);
        return _categorie;
    }

   

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
