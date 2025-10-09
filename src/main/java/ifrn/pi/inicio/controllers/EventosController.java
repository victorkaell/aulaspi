package ifrn.pi.inicio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pi.inicio.models.Convidado;
import ifrn.pi.inicio.models.Evento;
import ifrn.pi.inicio.repositories.ConvidadoRepository;
import ifrn.pi.inicio.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {
	
	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;
	
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
	public ModelAndView listarEventos() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("eventos/lista");
		mv.addObject("eventos", eventos);
		
		System.out.println("Chamou o método index");
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalharEvento(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		
		if (opt.isEmpty()) {
			mv.setViewName("redirect:/eventos");
			return mv;
		}
		
		mv.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		mv.addObject("evento", evento);
		
		return mv;
	}
	
	@PostMapping("/{idEvento}")
	public String salvarConvidado(@PathVariable Long idEvento, Convidado convidado) {
		
		System.out.println("ID do Evento: " + idEvento);
		System.out.println(convidado);
		
		Optional<Evento> opt = er.findById(idEvento);
		if(opt.isEmpty()) {
			return "redirect:/eventos";
		}
		
		Evento evento = opt.get();
		convidado.setEvento(evento);
		
		cr.save(convidado);
		
		return "redirect:/eventos/{idEvento}";
	}
}
