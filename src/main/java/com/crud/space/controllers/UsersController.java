package com.crud.space.controllers;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crud.space.models.Users;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class UsersController {
    private static List<Users> users = new ArrayList<>();
    static {
    	users.add(new Users("nicolas","orellana","2321","2023-01-02",1,true));//usuario
    	users.add(new Users("nicolas2","orellana","2321","2023-01-02",1,true));//usuario 2
    	users.add(new Users("prueba2","orellana","2321","2023-01-02",0,true));//administrador
    	users.add(new Users("prueba3","orellana","2321","2023-01-02",0,true));//administrador 2
    	users.add(new Users("prueba3","orellana","2321","2023-01-02",1,false)); //inactivo
    	users.add(new Users("prueba4","orellana","2321","2023-03-12",1,true)); //no ha pasado un mes
    }
    @GetMapping("/")
    public String getMainpage(Model model) {
    	String time = "mundo";
    	model.addAttribute("time", time);
    	
    	return "index";
    }
    @PostMapping("/users")
    public String saveUser(@RequestParam("firstName") String firstName, 
                           @RequestParam("password") String password,HttpSession session) {
    	String helper = "404";
    	for(Users u: users) {
    		LocalDate fechaActual = LocalDate.now();
    		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		LocalDate fecha = LocalDate.parse(u.getFecha(), formato);
    		Period periodo = Period.between(fecha, fechaActual);
    		int dias = periodo.getDays();
    		int meses = periodo.getMonths();
    		System.out.println(periodo);
    		if(u.getNombre().equals(firstName) && u.getPassword().equals(password) && u.getEstado() != false && (dias > 30 || meses >=1)) {
    		
    			
    			if(u.getRol() == 1) {
    				String mensaje = "Usuario";
    				session.setAttribute("mensaje", mensaje);
    				List<Users> usuariosFiltrados = users.stream()
    					    .filter(a -> a.getRol() == 1)
    					    .filter(a -> a.getEstado() == true)
    					    .collect(Collectors.toList());
    				session.setAttribute("usuarios", usuariosFiltrados);
    			}else {
    				String mensaje = "Administrador";
    				session.setAttribute("mensaje", mensaje);
    				List<Users> usuariosFiltrados = users.stream()
    					    .filter(a -> a.getRol() == 0)
    					    .filter(a -> a.getEstado() == true)
    					    .collect(Collectors.toList());
    				session.setAttribute("usuarios", usuariosFiltrados);
    			}
    			helper = "home";
    		}
    	}

    	  return "redirect:/"+helper;
      
    }
    @GetMapping("/404")
    public String show404(Model model) {
    	String time = "mundo";
    	model.addAttribute("time", time);
    	
    	return "404";
    }
    @GetMapping("/home")
    public String getHomepage(Model model,  HttpSession session) {
    	String time = "mundo";
    	String mensaje = (String) session.getAttribute("mensaje");
    	List<Users> usuarios = (List<Users>) session.getAttribute("usuarios");
    	model.addAttribute("usuarios",usuarios);
    	model.addAttribute("mensaje", mensaje);

    	return "home";
    }
}
