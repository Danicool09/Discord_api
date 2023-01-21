package com.example.discord_api.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Permisos {
    @Id
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    private String nombre;
    @NotNull
    @Enumerated
    private Admin Admin;



    @Override
    public String toString() {
        return "Peticion{" +
                "id=" + usuario +
                ", titulo='" + nombre + '\'' +
                ", admin='" + Admin + '\'' +
                '}';
    }






}


