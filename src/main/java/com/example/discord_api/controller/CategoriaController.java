package com.example.discord_api.controller;

import com.example.discord_api.domain.Perfil;
import com.example.discord_api.payload.Response;
import com.example.discord_api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Perfil>> listaCategorias() {
        List<Perfil> perfils = categoriaService.findAllByCategoria();
        return new ResponseEntity<>(perfils, HttpStatus.OK);
    }
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable Long id) {
        Optional<Perfil> categorias = categoriaService.findById(id);
        return new ResponseEntity<>(categorias.get(), HttpStatus.OK);
    }

    @DeleteMapping("/categorias/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable Long id){
        categoriaService.deleteCategory(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }
    @PostMapping("/categorias/post")
    public ResponseEntity<Response> addPeticion(@RequestBody Perfil perfil){
        categoriaService.addCategory(perfil);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }
    @GetMapping("/categorias/{code}")
    public boolean existePeticion(@PathVariable Long code) {
        categoriaService.existeCategory(code);
        if (existePeticion(code)) return true;
        return false;

    }

    @PostMapping("/categorias/modify")
    public Perfil modifyPeticion(@RequestBody Perfil newPerfil) {
        categoriaService.modifyCategory(newPerfil);
        return newPerfil;
    }
}