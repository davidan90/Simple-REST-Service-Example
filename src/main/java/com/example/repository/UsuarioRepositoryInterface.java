package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Usuario;

public interface UsuarioRepositoryInterface extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByEmail(String email);
}
