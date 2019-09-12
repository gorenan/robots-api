package br.com.robots.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bots")
public class RobotsController {

	@GetMapping("/{id}")
	public String getBots(@PathVariable String id) {
		System.out.println(id);
		return id;
	}
	
}
