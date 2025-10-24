package ifrn.pi.inicio.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pi.inicio.models.Convidado;
import ifrn.pi.inicio.models.Evento;
import ifrn.pi.inicio.repositories.ConvidadoRepository;
import ifrn.pi.inicio.repositories.EventoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;

	@GetMapping("/form")
	public String form(Evento evento) {
		return "eventos/formEvento";
	}

	@PostMapping
	public String salvarEvento(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return form(evento);
		}
		
		System.out.println(evento);
		er.save(evento);
		
		attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");

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

	@GetMapping("/{idEvento}")
	public ModelAndView detalharEvento(@PathVariable Long idEvento, Convidado convidado) {
		ModelAndView mv = new ModelAndView();
		Optional<Evento> opt = er.findById(idEvento);

		if (opt.isEmpty()) {
			mv.setViewName("redirect:/eventos");
			return mv;
		}

		mv.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		mv.addObject("evento", evento);

		List<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);

		return mv;
	}

	@PostMapping("/{idEvento}")
	public ModelAndView salvarConvidado(@PathVariable Long idEvento, @Valid Convidado convidado, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("ID do Evento: " + idEvento);
		System.out.println(convidado);
		
		if (result.hasErrors()) {
			return detalharEvento(idEvento, convidado);
		}

		Optional<Evento> opt = er.findById(idEvento);
		if (opt.isEmpty()) {
			mv.setViewName("redirect:/eventos");
			return mv;
		}

		Evento evento = opt.get();
		convidado.setEvento(evento);
		
		System.out.println(convidado);
		
		cr.save(convidado);
		
		mv.setViewName("redirect:/eventos/{idEvento}");

		return mv;
	}
	
	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarEvento(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		
		Optional<Evento> opt = er.findById(id);
		
		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		
		Evento evento = opt.get();
		md.setViewName("eventos/formEvento");
		md.addObject("evento", evento);
		
		return md;
	}
	
	@GetMapping("/{idEvento}/convidados/{idConvidado}/selecionar")
	public ModelAndView selecionarConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
		ModelAndView md = new ModelAndView();
		
		Optional<Evento> optEvento = er.findById(idEvento);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);
		
		if (optEvento.isEmpty() || optConvidado.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		
		Evento evento = optEvento.get();
		Convidado convidado = optConvidado.get();
		
		if (evento.getId() != convidado.getEvento().getId()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		
		md.setViewName("eventos/detalhes");
		md.addObject("convidado", convidado);
		md.addObject("evento", evento);
		md.addObject("convidados", cr.findByEvento(evento));
	
		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id, RedirectAttributes attributes) {
		Optional<Evento> opt = er.findById(id);

		if (!opt.isEmpty()) {
			Evento evento = opt.get();

			List<Convidado> convidados = cr.findByEvento(evento);
			cr.deleteAll(convidados);

			er.delete(evento);
			
			attributes.addFlashAttribute("mensagem", "Evento removido com sucesso!");
		}

		return "redirect:/eventos";
	}
	
	@GetMapping("/{idEvento}/convidados/{idConvidado}/remover")
	public String apagarConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
		Optional<Evento> optEvento = er.findById(idEvento);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);
		
		if (optEvento.isEmpty() || optConvidado.isEmpty()) {
			return "redirect:/eventos";
		}
		
		Convidado convidado = optConvidado.get();
		cr.delete(convidado);
		
		return "redirect:/eventos/" + idEvento;
	}
}
