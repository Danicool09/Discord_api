package com.example.discord_api.repository;

import com.example.discord_api.domain.Permisos;
import com.example.discord_api.domain.Servidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeticionesRepository extends JpaRepository<Servidor, Long>{
    @Query("SELECT p FROM Permisos p WHERE p.Admin = 0")
    List<Permisos> findAllByEstadoPeticion();


    Page<Servidor> findAll(Specification<Servidor> specs, Pageable pageable);


}