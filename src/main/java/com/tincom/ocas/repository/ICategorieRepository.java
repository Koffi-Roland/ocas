/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.repository;

import com.tincom.ocas.entities.Categorie;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *  
 * @author roland
 */
public interface ICategorieRepository extends JpaRepository<Categorie, Long>{
    
     Optional <Categorie> findOneById(Long id);
     Optional <Categorie> findOneByLibelle(String libelle);
    // Optional <Categorie> findOneByDescription(Blob description);
    @Query("SELECT c FROM Categorie c  ORDER BY c.libelle")
    public List<Categorie> getAllCategorieOrdered();
     

}
