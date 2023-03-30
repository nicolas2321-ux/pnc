package com.crud.space;
import com.crud.space.Users;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UsersController {
    private static List<Users> users = new ArrayList<>();
    static {
    	users.add(new Users("nicolas","orellana","2321","2023-01-02",1,true));
    	users.add(new Users("prueba2","orellana","2321","2023-01-02",0,true));
    	users.add(new Users("prueba3","orellana","2321","2023-01-02",1,true));
    	users.add(new Users("prueba4","orellana","2321","2023-01-02",1,true));
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
    	String helper = "";
    	for(Users u: users) {
    		if(u.getNombre().equals(firstName) && u.getPassword().equals(password)) {
    			if(u.getRol() == 1) {
    				String mensaje = "Usuario";
    				session.setAttribute("mensaje", mensaje);
    			}else {
    				String mensaje = "Administrador";
    				session.setAttribute("mensaje", mensaje);
    			}
    			helper = "home";
    		}
    	}
    	  return "redirect:/"+helper;
      
    }
    @GetMapping("/home")
    public String getHomepage(Model model,  HttpSession session) {
    	String time = "mundo";
    	   String mensaje = (String) session.getAttribute("mensaje");
    	model.addAttribute("mensaje", mensaje);
    	
    	return "home";
    }
}
