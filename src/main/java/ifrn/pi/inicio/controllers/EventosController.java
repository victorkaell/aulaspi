package ifrn.pi.inicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ifrn.pi.inicio.models.Evento;

@Controller
public class EventosController {
	
	@GetMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
	
	@PostMapping("/eventos/adicionar")
	public String adicionarEvento(Evento evento) {
		System.out.println("Evento foi adicionado");
		System.out.println("Nome: " + evento.getNome());
		System.out.println("Local: " + evento.getLocal());
		System.out.println("Data: " + evento.getData());
		System.out.println("Horário: " + evento.getHorario());
		
		// Ao realizar o passo 1, o evento foi registrado com a devida confirmação e os dados foram enviados.
		// Ao realizar o passo 2, foi possível obter os valores pelo String e imprimi-los no console.
		// Ao realizar o passo 3, os valores continuaram os mesmos após simplificar o parâmetro.
		
		return "eventoAdicionado";
	}
}
