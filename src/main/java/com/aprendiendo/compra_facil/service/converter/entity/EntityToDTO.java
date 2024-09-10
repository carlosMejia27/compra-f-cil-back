package com.aprendiendo.compra_facil.service.converter.entity;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTO implements Converter<Cliente, ClienteDto> {
    @Override
    public ClienteDto convert(Cliente source) {
        if (source == null) {
            return null;
        }
        return ClienteDto.builder()
                .nombre(source.getNombre())
                .apellido(source.getApellido())
                .email(source.getEmail())
                .telefono(source.getTelefono())
                .direccion(source.getDireccion())
                .fechaRegistro(source.getFechaRegistro())
                .page(0)
                .size(10)
                .build();

    }
}

