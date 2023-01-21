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
public class Servidor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nombre;
    @NotNull
    private int Usuarios;

    private int permisos;

    private String photo;



    @Override
    public String toString() {
        return "Peticion{" +
                "id=" + id +
                ", titulo='" + nombre + '\'' +
                ", destinatario='" + Usuarios + '\'' +
                ", firmantes=" + permisos +
                '}';
    }
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;



}


