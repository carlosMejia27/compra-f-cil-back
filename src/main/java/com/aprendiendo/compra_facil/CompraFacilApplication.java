package com.aprendiendo.compra_facil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class CompraFacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraFacilApplication.class, args);
	}


//	@Autowired
//	ClienteRepositorio repositorio;
//	@Autowired
//	ClienteServicio servicio = null;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//
//
//		ClienteDto dto = new ClienteDto();
//		dto.setNombre("Ana");
//		dto.setPage(0);
//		dto.setSize(10);
//
//		// Obtener la respuesta del servicio
//		var respuesta = servicio.filter(dto, SortType.NOMBRE);
//
//		// Log del resultado
//		log.info("Resultado del filtro: {}", respuesta);
//
//		// Log detallado de cada cliente en la respuesta
//		respuesta.forEach(clienteDto -> log.info("Cliente DTO: {}", clienteDto));
//	}
}