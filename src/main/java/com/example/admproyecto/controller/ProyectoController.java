package com.example.admproyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.admproyecto.model.Proyecto;
import com.example.admproyecto.servicios.ProyectoServicios;

@Controller
public class ProyectoController {
	
	@Autowired
	private ProyectoServicios pryServ;
	
	@GetMapping("/proyectos")
	public String listarProyectos(Model modelo){
		modelo.addAttribute("proyectos", pryServ.listarProyectos());
		return "proyectos";
	}
	
	@GetMapping("/proyectos/nuevo")
	public String mostrarVistaRegistro(Model modelo) {
		Proyecto proyecto = new Proyecto();
		modelo.addAttribute("proyecto", proyecto);
		return "proyectoNuevo";
	}
	
	@PostMapping("/proyectos/nuevo")
	public String registrarProyecto(@ModelAttribute("proyecto") Proyecto proyecto) {
		pryServ.registrarProyecto(proyecto);
		return "redirect:/proyectos";
	}
	
	@GetMapping("/proyectos/actualizar/{id}")
	public String mostrarVistaActualizar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("proyecto", pryServ.obtenerProyectoID(id));
		return "actualizarProyecto";
	}
	
	@PostMapping("/proyectos/{id}")
	public String actualizarProyecto(@PathVariable Integer id, @ModelAttribute("proyecto") Proyecto proyecto, Model modelo) {
		Proyecto proyectoExistente = pryServ.obtenerProyectoID(id);
		proyectoExistente.setId(id);
		proyectoExistente.setNombre(proyecto.getNombre());
		proyectoExistente.setEstado(proyecto.getEstado());
		pryServ.actualizarProyecto(proyectoExistente);
		return "redirect:/proyectos";
	}
	
	@GetMapping("/proyectos/{id}")
	public String eliminarProyecto(@PathVariable Integer id) {
		pryServ.eliminarProyecto(id);
		return "redirect:/proyectos"; 
	}
}
