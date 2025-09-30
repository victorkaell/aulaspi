package ifrn.pi.inicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ifrn.pi.inicio.models.Evento;
import ifrn.pi.inicio.repositories.EventoRepository;

@Controller
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	
	@GetMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
	
	@PostMapping("/eventos/adicionar")
	public String adicionarEvento(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		
		return "eventoAdicionado";
	}
}
