package ifrn.pi.inicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ifrn.pi.inicio.models.Evento;
import ifrn.pi.inicio.repositories.EventoRepository;

@Controller
public class IndexController {
	
	@Autowired
	private EventoRepository er;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Evento> eventos = er.findAll();
		model.addAttribute("eventos", eventos);
		
		System.out.println("Chamou o método index");
		
		return "redirect:/eventos";
	}
}
