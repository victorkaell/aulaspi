package ifrn.pi.inicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventosController {
	
	@GetMapping("/eventos/form")
	public String form() {
		return "formEvento";
	}
}
