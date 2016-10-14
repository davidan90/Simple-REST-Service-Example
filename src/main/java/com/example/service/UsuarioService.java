package com.example.service;

import java.util.List;

import com.example.model.Usuario;

public interface UsuarioService {
	
	void createUsuario(Usuario usuario);
	
	void deleteUsuario(Usuario usuario);
	
	void updateUsuario(Usuario usuario);
	
	Usuario findUsuarioById(int id);
	
	Usuario findUsuarioByEmail(String email);
	
	List<Usuario> findAllUsuario();

	
}
