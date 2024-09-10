package com.aprendiendo.compra_facil.respository;


import com.aprendiendo.compra_facil.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
@Query("""
        select c
        from Cliente c
        where (:nombre is null or c.nombre like %:nombre%)
        and (:apellido is null or c.apellido like %:apellido%)
        """ )
Page<Cliente> filter(
                          @Param("nombre") final String nombre,
                          @Param("apellido") final String apellido,
                          final Pageable pageable);

}


