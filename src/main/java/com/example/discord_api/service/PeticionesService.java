package com.example.discord_api.service;

import com.example.discord_api.domain.Permisos;
import com.example.discord_api.domain.Servidor;
import com.example.discord_api.repository.PeticionesRepository;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeticionesService extends BaseService<Servidor, Long, PeticionesRepository> {

    @Autowired
    private PeticionesRepository peticionesRepository;

    public Boolean existePeticion(Long code) {
        if (peticionesRepository.existsById(code)) return true;
        return false;
    }

    public Servidor addPeticion(Servidor servidor){
        return peticionesRepository.save(servidor);
    }

    public Servidor modifyPeticion(Servidor newServidor) {
        Optional<Servidor> peticion = null;
        try{
            peticion= peticionesRepository.findById(newServidor.getId());
            if (peticion.isPresent()){
                peticionesRepository.save(newServidor);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return peticion.get();
    }

    public void deletePeticion(Long id) {
        try{ peticionesRepository.delete(peticionesRepository.findById(id).get()); }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Servidor cambiarEstadoPeticion(Servidor newEstado) {
        Optional<Servidor> peticion = null;
        try{
            peticion= peticionesRepository.findById(newEstado.getId());
            if (peticion.isPresent()){
                peticionesRepository.save(newEstado);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return peticion.get();
    }

    public List<Permisos> findAllByEstadoPeticion() {
        List<Permisos> permisos = (List<Permisos>)
                peticionesRepository.findAllByEstadoPeticion();
        return permisos;
    }

    public Servidor firmarPeticion(Servidor newEstado) {
        Optional<Servidor> peticion = null;
        try{
            peticion= peticionesRepository.findById(newEstado.getId());
            if (peticion.isPresent()){
                peticionesRepository.save(newEstado);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return peticion.get();
    }

    public Page<Servidor> getPeticiones(Pageable pageable, @SearchSpec
    Specification<Servidor> specs){
        return peticionesRepository.findAll(specs, pageable);
    }


}
