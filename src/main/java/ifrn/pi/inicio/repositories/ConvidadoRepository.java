package ifrn.pi.inicio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.pi.inicio.models.Convidado;
import ifrn.pi.inicio.models.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {
	List<Convidado> findByEvento(Evento evento);
}
