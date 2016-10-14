package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	private int idUsuario;
	
	@Column(name = "name", nullable=false)
	private String name;
	
	@Column(name = "password", nullable=false)
	private String password;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "email", nullable=false, unique=true)
	private String email;
	
	public Usuario() {}
	
	public Usuario(String name, String password, int edad, String email) {
		super();
		this.name = name;
		this.password = password;
		this.edad = edad;
		this.email = email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario {idUsuario:" + idUsuario + ", name:" + name + ", password:" + password + ", edad:" + edad
				+ ", email:" + email + "}";
	}
	
	
	
}
