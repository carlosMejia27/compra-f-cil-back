package com.aprendiendo.compra_facil.service.converter.dto;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class DtoToEntity implements Converter<ClienteDto, Cliente> {
    @Override
    public Cliente convert(ClienteDto source) {
        if (source == null) {
            return null;
        }
        return Cliente.builder()
                .nombre(source.getNombre())
                .apellido(source.getApellido())
                .email(source.getEmail())
                .telefono(source.getTelefono())
                .direccion(source.getDireccion())
                .fechaRegistro(source.getFechaRegistro())
                .build();

    }
}
