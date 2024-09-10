package com.aprendiendo.compra_facil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    public String nombre ;

    public String apellido ;

    public String email;

    public String  telefono;

    public String direccion ;

    @Column(name = "fecha_registro")
    public LocalDate fechaRegistro ;
}
