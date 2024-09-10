package com.aprendiendo.compra_facil.service.converter.dto;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;
import com.aprendiendo.compra_facil.util.SortType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                DtoToEntity.class
        })
class DtoToEntityTest {

    private final DtoToEntity converter = new DtoToEntity();



    @Test
    void convert_ShouldReturnNull_WhenDtoToEntityIsNull() {
        // Given
        final DtoToEntity converter = this.converter;

        // When
        final Cliente cliente = converter.convert(null);

        // Then
        assertNull(cliente);
    }

    @Test
    void convert_ShouldReturnCrhbCategoryDto_WhenCrhbCategoryExists() {
        // Given
        final ClienteDto clienteDto= ClienteDto.builder()
                .nombre("Carlos")
                .apellido("Mejia")
                .email("carlos@gmail.com")
                .telefono("686115087")
                .direccion("leonor golfin 32")
                .fechaRegistro(LocalDate.of(1993, 11, 11))
                .page(0)
                .size(10)
                .sortType(SortType.APELLIDO)
                .build();

        // When
        final Cliente cliente = this.converter.convert(clienteDto);

        // Then
        assertNotNull(cliente);
        assertEquals(clienteDto.getNombre(), cliente.getNombre());
        assertEquals(clienteDto.getApellido(), cliente.getApellido());
        assertEquals(clienteDto.getEmail(), cliente.getEmail());
        assertEquals(clienteDto.getTelefono(), cliente.getTelefono());
        assertEquals(clienteDto.getDireccion(), cliente.getDireccion());
        assertEquals(clienteDto.getFechaRegistro(), cliente.getFechaRegistro());

    }

}