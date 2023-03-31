package com.crud.space.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@AllArgsConstructor




public class Users {
	private String nombre;
	private String apellido;
	private String password;
	private String fecha;
	private Integer rol;
	private Boolean estado;
	
	 
}
