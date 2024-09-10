package com.aprendiendo.compra_facil.service;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;
import com.aprendiendo.compra_facil.respository.ClienteRepositorio;
import com.aprendiendo.compra_facil.service.converter.dto.DtoToEntity;
import com.aprendiendo.compra_facil.service.converter.entity.EntityToDTO;
import com.aprendiendo.compra_facil.util.SortType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {
                ClienteServicio.class,
                DefaultConversionService.class,
                DtoToEntity.class,
                EntityToDTO.class,
        })
class ClienteServicioTest {

    @MockBean
    private ClienteRepositorio clienteRepositorio;

    @MockBean
    private ConversionService conversionService;

    @Autowired
    private ClienteServicio clienteServicio;

    Sort sort = Sort.by(Sort.Direction.ASC, "apellido");

    private LocalDate fechaLocalDate = LocalDate.of(2024, 9, 9); // Fecha específica (Año, Mes, Día)


    private static Stream<Arguments> givenFilterForTest() {
        return Stream.of(
                Arguments.of("Carlos", "Mejia", Sort.by(Sort.Direction.ASC, "apellido"), SortType.APELLIDO),
                Arguments.of("Maria", "Perez", Sort.by(Sort.Direction.ASC, "nombre"), SortType.NOMBRE)


        );
    }


    @ParameterizedTest
    @MethodSource("givenFilterForTest")
    void testFilterParametrized(String nombre, String apellido, Sort sort, SortType sortType) {
        // Configuramos los mocks
        Pageable pageable = PageRequest.of(0, 10, sort);

        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .build();

        ClienteDto clienteDto=ClienteDto.builder()
                .nombre(nombre)
                .apellido(apellido)
                .build();

        Page<Cliente> clientePage = new PageImpl<>(Collections.singletonList(cliente), pageable, 1);

        when(clienteRepositorio.filter(nombre, apellido, pageable)).thenReturn(clientePage);

//       when(conversionService.convert(any(Cliente.class), eq(ClienteDto.class)))
//               .thenReturn(clienteDto);


        // Ejecutamos el servicio con los parámetros del test
        Page<ClienteDto> result = clienteServicio.filter(new ClienteDto(nombre, apellido),sortType );

        verify(this.conversionService, times(1)).convert(cliente, ClienteDto.class);
        verify(this.clienteRepositorio, times(1)).filter(
                argThat(n -> n.equals(nombre)),
                argThat(a -> a.equals(apellido)),
                argThat(p -> p.equals(pageable))
        );

        assertNotNull(result);
    }

    private Cliente createCliente() {
        return Cliente.builder()
                .nombre("Carlos")
                .apellido("Mejia")
                .email("carlos@gmail.com")
                .telefono("686115087")
                .direccion("leonor golfin 32")
                .fechaRegistro(fechaLocalDate)
                .build();
    }

    private ClienteDto createClienteDto() {
        return ClienteDto.builder()
                .nombre("Carlos")
                .apellido("Mejia")
                .email("carlos@gmail.com")
                .telefono("686115087")
                .direccion("leonor golfin 32")
                .fechaRegistro(fechaLocalDate)
                .page(0)
                .size(10)
                .sortType(SortType.APELLIDO)
                .build();
    }

    private Page<Cliente> createPageCliente(Cliente cliente, ClienteDto clienteDto) {
        // Configurar la paginación usando la información del ClienteDto
        Pageable pageable = PageRequest.of(clienteDto.getPage(), clienteDto.getSize(), sort);
        return new PageImpl<>(Collections.singletonList(cliente), pageable, 1);

        // Crear el objeto Page con el cliente y el Pageable
    }




}