package com.aprendiendo.compra_facil.dto;

import com.aprendiendo.compra_facil.util.SortType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteDto   {

    public String nombre ;
    public String apellido ;
    public String  telefono;
    public String email;
    public String direccion ;
    public LocalDate fechaRegistro ;
    private int page = 0;
    private int size = 10;
    private SortType sortType ;

    public ClienteDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;;
    }
}
