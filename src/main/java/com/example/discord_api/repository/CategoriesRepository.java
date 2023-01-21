package com.example.discord_api.repository;

import com.example.discord_api.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Perfil, Long>{
    @Query("SELECT c FROM Perfil c")
    List<Perfil> findAllByCategoria();


}