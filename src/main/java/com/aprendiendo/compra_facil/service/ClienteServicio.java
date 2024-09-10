package com.aprendiendo.compra_facil.service;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.entity.Cliente;
import com.aprendiendo.compra_facil.respository.ClienteRepositorio;
import com.aprendiendo.compra_facil.util.SortType;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServicio {


    private final ClienteRepositorio clienteRepositorio;

    private final ConversionService conversionService;


    public Page<ClienteDto> filter(ClienteDto filtroDto, SortType ordenar) {
        

        // Crear el objeto Sort basado en el tipo de ordenamiento
        Sort sort = createSort(ordenar);
        // creo pageable
        Pageable pageable = PageRequest.of(filtroDto.getPage(), filtroDto.getSize(),sort);

        Page<Cliente> clientePage = clienteRepositorio.filter(
                filtroDto.getNombre(),
                filtroDto.getApellido(),
                pageable
        );

        return clientePage.map(cliente -> {
            ClienteDto dto = conversionService.convert(cliente, ClienteDto.class);
            log.info("***************dto************* {}",dto);
            if (dto != null) {
                dto.setSortType(dto.getSortType()); // Asignar el SortType al DTO

            }
            return dto;

        });

    }

    private Sort createSort(SortType sortType) {
        switch (sortType) {
            case NOMBRE:
                return Sort.by(Sort.Direction.ASC, "nombre");
            case APELLIDO:
                return Sort.by(Sort.Direction.ASC, "apellido");
            default:
                return Sort.unsorted();
        }
    }
}


