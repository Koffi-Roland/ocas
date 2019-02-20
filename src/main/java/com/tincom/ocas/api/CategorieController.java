/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.api;

import com.codahale.metrics.annotation.Timed;
import com.tincom.ocas.api.util.HeaderUtil;
import com.tincom.ocas.api.util.PaginationUtil;
import com.tincom.ocas.entities.Categorie;
import com.tincom.ocas.repository.ICategorieRepository;
import com.tincom.ocas.service.ICategorieService;
import com.tincom.ocas.service.dto.CategorieDto;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roland
 */
@RestController
@RequestMapping("/api/categorie")
public class CategorieController {

    private final Logger log = LoggerFactory.getLogger(CategorieController.class);
    private static final String ENTITY_NAME = "categorieManagement";

    private final ICategorieRepository categorieRepository;

    private final ICategorieService categorieService;

    public CategorieController(ICategorieRepository categorieRepository, ICategorieService categorieService) {
        this.categorieRepository = categorieRepository;
        this.categorieService = categorieService;
    }

    @GetMapping("/list/pageable")
    //@Timed
    // @Secured(AuthoritiesConstants.USER_ADMIN)
    public ResponseEntity<List<Categorie>> getPageCategorie(@ApiParam Pageable pageable) {
        final Page<Categorie> page = categorieRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categorie/list");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/list")
    @Timed
    public ResponseEntity<List<Categorie>> getListCategorie() {
        final List<Categorie> list = categorieRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/new")
    //@Timed
    //@Secured(AuthoritiesConstants.USER_ADMIN)
    public ResponseEntity<Categorie> saveCategorie(@Valid @RequestBody CategorieDto categorie) throws URISyntaxException {
        log.debug("REST request to save categorie : {}", categorie);
        log.info("id à afficher"+categorie.getId());

        if (categorie.getId() != null ){
            if(categorieRepository.existsById(categorie.getId())){
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "categorie.error.idexist", "Id categorie existe déjà"))
                    .body(null);
            }
        } else if (categorieRepository.findOneByLibelle(categorie.getLibelle().toLowerCase()).isPresent()) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "categorie.error.libelleexist", "Ce libellé existe déjà"))
                    .body(null);
        }

            Categorie _categorie = categorieService.save(categorie);

            return ResponseEntity.created(
                    new URI("/api/categorie/new"))
                    .headers(HeaderUtil.createAlert("categorie.save", _categorie.getLibelle() + " avec success"))
                    .body(_categorie);

    }

    @PutMapping("/update")
    //@Timed
    //@Secured(AuthoritiesConstants.USER_ADMIN)
    public ResponseEntity<CategorieDto> updateCategorie(@Valid
            @RequestBody CategorieDto categorieDto
    ) throws URISyntaxException {
        log.debug("REST request to update Categorie : {}", categorieDto);
        Optional<Categorie> existingCategorie = categorieRepository.findOneById(categorieDto.getId());
        if (existingCategorie.isPresent() && (!existingCategorie.get().getId().equals(categorieDto.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "categorie.update.error", "Categorie deja utilisé")).body(null);
        }
        existingCategorie = categorieRepository.findOneByLibelle(categorieDto.getLibelle());
        if (existingCategorie.isPresent() && (!existingCategorie.get().getId().equals(categorieDto.getId()))) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "categorie.error.labelexist", "Categorie deja utilisé")).body(null);
        }

        Optional<CategorieDto> updatedCategorie = categorieService.update(categorieDto);

        return ResponseEntity.created(
                new URI("/api/categorie/update" + updatedCategorie.get().getId()))
                .headers(HeaderUtil.createAlert("categorie.update", Long.toString(updatedCategorie.get().getId()) + " avec success"))
                .body(updatedCategorie.get());

    }

    @DeleteMapping("/delete/{id}")
    //@Timed
    // @Secured(AuthoritiesConstants.USER_ADMIN)
    public ResponseEntity<Void> deleteFonction(@PathVariable Long id) {
        log.debug("REST request to delete User: {}", id);
        try {
            categorieService.delete(id);
            return ResponseEntity.ok().headers(HeaderUtil.createAlert("categorie.delete", Long.toString(id))).build();

        } catch (Exception e) {//error handling code
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, e.getMessage(), "categorie.error.delete")).build();
        }

    }

}
