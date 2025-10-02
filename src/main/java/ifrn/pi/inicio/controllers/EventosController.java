package ifrn.pi.inicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.inicio.models.Evento;
import ifrn.pi.inicio.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	
	@GetMapping("/form")
	public String form() {
		return "eventos/formEvento";
	}
	
	@PostMapping
	public String adicionarEvento(Evento evento) {
		System.out.println(evento);
		er.save(evento);
		
		return "eventos/eventoAdicionado";
	}
	
	@GetMapping
	public String listarEventos(Model model) {
		List<Evento> eventos = er.findAll();
		model.addAttribute("eventos", eventos);
		
		System.out.println("Chamou o método index");
		
		return "eventos/lista";
	}
}
