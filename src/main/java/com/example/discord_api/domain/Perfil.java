package com.example.discord_api.domain;

import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {
    @Id
    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    private String nombre;

    private String banner;

    private String vinculaciones;

    @Override
    public String toString() {
        return "Peticion{" +
                "id=" + usuario +
                ", titulo='" + nombre + '\'' +
                ", banner='" + banner + '\'' +
                ", firmantes=" + vinculaciones +
                '}';
    }

}
