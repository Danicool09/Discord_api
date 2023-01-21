package com.example.discord_api.controller;

import com.example.discord_api.domain.Perfil;
import com.example.discord_api.domain.Permisos;
import com.example.discord_api.domain.Servidor;
import com.example.discord_api.payload.Response;
import com.example.discord_api.service.CategoriaService;

import com.example.discord_api.service.FileStorageService;
import com.example.discord_api.service.PeticionesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "*")
public class PeticionesController {

    @Autowired
    private PeticionesService peticionesService;

    @Autowired
    private CategoriaService categoriaService;




    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/peticiones/{id}")
    public ResponseEntity<Servidor> findById(@PathVariable Long id) {
        Optional<Servidor> peticion = peticionesService.findById(id);
        return new ResponseEntity<>(peticion.get(), HttpStatus.OK);
    }

    @DeleteMapping("/peticiones/delete/{id}")
    public ResponseEntity<Response> deletePeticion(@PathVariable Long id) {
        peticionesService.deletePeticion(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @PostMapping("/peticiones/post")
    public ResponseEntity<Response> addPeticion(@RequestBody Servidor servidor) {
        peticionesService.addPeticion(servidor);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @GetMapping("/peticiones/{code}")
    public boolean existePeticion(@PathVariable Long code) {
        peticionesService.existePeticion(code);
        if (existePeticion(code)) return true;
        return false;
    }

    @PostMapping("/peticiones/modify")
    public Servidor modifyPeticion(@RequestBody Servidor newServidor) {
        peticionesService.modifyPeticion(newServidor);
        return newServidor;
    }

    @PutMapping("/peticiones/extra/{id}")
    public Servidor cambiarEstadoPeticion(@RequestBody Servidor newEstado) {
        peticionesService.modifyPeticion(newEstado);
        return newEstado;
    }


    @GetMapping("/peticiones/page")
    public ResponseEntity<Page<Servidor>>
    listaPeticiones(@PageableDefault(size=10, page = 0) Pageable pageable) {

        Page<Servidor> peticiones = peticionesService.findAll(pageable);
        if ( pageable.getPageNumber() > peticiones.getTotalPages()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(peticiones, HttpStatus.OK);
    }

    @GetMapping("/peticiones/filtro")
    public ResponseEntity<Page<Servidor>>
    listaPeticiones(@PageableDefault(size=10, page = 0) Pageable pageable,
                    @SearchSpec Specification<Servidor> specs) {

        Page<Servidor> peticiones =
                peticionesService.getPeticiones(pageable,specs);
        if ( pageable.getPageNumber() > peticiones.getTotalPages()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(peticiones, HttpStatus.OK);
    }

    @PostMapping(path="/peticiones/{user_id}/{categora_id}")
    public ResponseEntity<Servidor> addPeticion(@RequestParam("peticion")
                                                    String jsonObject, @RequestParam("file") MultipartFile file, @PathVariable Long user_id, @PathVariable Long categora_id) {

        Servidor servidor =null;
        try{
            String fileName = fileStorageService.storeFile(file);
            servidor = objectMapper.readValue(jsonObject, Servidor.class);
            Optional<Perfil> categoria=
                    categoriaService.findById(categora_id);
            if (categoria.isPresent()){

                servidor.setUsuario(categoria.get().getUsuario());
                servidor.setPhoto(fileName);
                Servidor servidor1 = peticionesService.addPeticion(servidor);
                return new ResponseEntity<>(servidor1, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}

