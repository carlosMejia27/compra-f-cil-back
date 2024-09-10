package com.aprendiendo.compra_facil.controllrs;

import com.aprendiendo.compra_facil.dto.ClienteDto;
import com.aprendiendo.compra_facil.service.ClienteServicio;
import com.aprendiendo.compra_facil.util.SortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClientControlls {

    private final ClienteServicio clienteServicio;

    @PostMapping("/filter")
    public ResponseEntity<Page<ClienteDto>> findAll(
            @RequestBody ClienteDto clienteDto,
            @RequestParam SortType ordenar) {

        // Obtener la lista de clientes filtrado
        var response = this.clienteServicio.filter(clienteDto, ordenar);
        return ResponseEntity.ok(response);

    }
}
