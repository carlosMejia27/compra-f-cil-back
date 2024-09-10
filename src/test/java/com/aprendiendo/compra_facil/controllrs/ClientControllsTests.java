package com.aprendiendo.compra_facil.controllrs;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;
import com.aprendiendo.compra_facil.service.ClienteServicio;
import com.aprendiendo.compra_facil.util.SortType;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

@WebMvcTest({ClientControlls.class})
@MockBean({ClienteServicio.class})
class ClientControllsTests {
    private final ClienteServicio service;

    private LocalDate fechaLocalDate = LocalDate.of(2024, 9, 9); // Fecha específica (Año, Mes, Día)

    public static final String BASE_MAPPING = "/api/clientes";
    private static final String GET_BY_FILTER_MAPPING = "/filter?ordenar=APELLIDO";
    private static final String ORDENAR = "APELLIDO";

    @Autowired
    public ClientControllsTests(final WebApplicationContext webApplicationContext,
                               final ClienteServicio service) {
        this.service = service;
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    void findAll_shouldReturnOk_whenCrhbCategoriesExists() {
        // Given
        final ClienteDto filter = this.createClienteDto();
        final Page<ClienteDto> page = this.createPaginacion(filter);


        // When
        given(this.service.filter(any(ClienteDto.class), eq(SortType.APELLIDO))).willReturn(page);

        // When
        RestAssuredMockMvc
                .given()
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8))
                .body(filter)
                .when()
                .post(BASE_MAPPING + GET_BY_FILTER_MAPPING)
                .then()
                .log().ifValidationFails()
                .statusCode(OK.value())
                .body("content.nombre", hasItems(filter.getNombre()));

        // Verificar que el servicio fue llamado una vez con cualquier instancia de ClienteDto y SortType.APELLIDO
        verify(this.service, times(1)).filter(any(ClienteDto.class), eq(SortType.APELLIDO));

    }



    private Cliente createCliente() {
        return Cliente.builder()
                .nombre("Carlos2")
                .apellido("Mejia")
                .telefono("686115087")
                .direccion("leonor golfin 32")
                .fechaRegistro(fechaLocalDate)
                .build();
    }
    private ClienteDto createClienteDto() {
        return ClienteDto.builder()
                .nombre("Carlos")
                .apellido("Mejia")
//                .telefono("686115087")
//                .direccion("leonor golfin 32")
//                .fechaRegistro(fechaLocalDate)
                .page(0)
                .size(10)
                .sortType(SortType.APELLIDO)
                .build();
    }

    private Page<ClienteDto> createPaginacion(ClienteDto clienteDto) {
        PageRequest pageRequest = PageRequest.of(clienteDto.getPage(), clienteDto.getSize(), Sort.by(clienteDto.getSortType().name()));
        return new PageImpl<>(Collections.singletonList(clienteDto), pageRequest, 1);

}
}