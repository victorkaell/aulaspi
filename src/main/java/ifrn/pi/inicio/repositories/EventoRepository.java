package ifrn.pi.inicio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.inicio.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
