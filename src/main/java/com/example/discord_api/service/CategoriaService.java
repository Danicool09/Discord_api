package com.example.discord_api.service;



import com.example.discord_api.domain.Perfil;
import com.example.discord_api.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService extends BaseService<Perfil, Long, CategoriesRepository> {
    @Autowired
    private CategoriesRepository categoriesRepository;
    public Boolean existeCategory(Long code) {
        if (categoriesRepository.existsById(code)) return true;
        return false;
    }

    public Perfil addCategory(Perfil perfil){
        return categoriesRepository.save(perfil);
    }

    public Perfil modifyCategory(Perfil newCategori) {
        Optional<Perfil> categoria = null;
        try{
            categoria= categoriesRepository.findById(newCategori.getUsuario().getId());
            if (categoria.isPresent()){
                categoriesRepository.save(newCategori);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return categoria.get();
    }

    public void deleteCategory(Long id) {
        try{
            categoriesRepository.delete(categoriesRepository.findById(id).get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Perfil> findAllByCategoria() {
        List<Perfil> perfil = (List<Perfil>)
                categoriesRepository.findAllByCategoria();
        return perfil;
    }
}

