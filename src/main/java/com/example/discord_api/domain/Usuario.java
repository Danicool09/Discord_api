package com.example.discord_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String username;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Servidor> servidor;
    @OneToOne(cascade ={ CascadeType.ALL})
    @JoinColumn(name = "id_usuario")
    private Permisos permisos;

}