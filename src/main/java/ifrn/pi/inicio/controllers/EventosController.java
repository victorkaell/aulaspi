package ifrn.pi.inicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventosController {
	
	@GetMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
	
	@PostMapping("/eventos/adicionar")
	public String adicionarEvento() {
		System.out.println("Evento foi adicionado");
		
		return "eventoAdicionado";
	}
}
