package ifrn.pi.inicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		System.out.println("Chamou o método index");
		return "home";
	}
}