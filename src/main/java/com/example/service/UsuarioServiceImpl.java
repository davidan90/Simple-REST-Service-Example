package com.example.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Usuario;
import com.example.repository.UsuarioRepositoryInterface;

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	static final Logger logger = LogManager.getLogger(UsuarioServiceImpl.class.getName());

	@Autowired
	private UsuarioRepositoryInterface usuarioRepository;
		
	@Override
	public void createUsuario(Usuario usuario) {
		try{
			usuarioRepository.save(usuario);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		try{
			usuarioRepository.delete(usuario);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		try{
			Usuario u = findUsuarioByEmail(usuario.getEmail());
			if(u!=null){
				u.setName(usuario.getName());
				u.setEdad(usuario.getEdad());
				u.setPassword(usuario.getPassword());
				u.setEmail(usuario.getEmail());
				usuarioRepository.save(u);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public Usuario findUsuarioById(int id) {
		try {
			Usuario u = usuarioRepository.findOne(id);
			return u;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Usuario findUsuarioByEmail(String email) {
		try {
			Usuario u = usuarioRepository.findByEmail(email);
			return u;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Usuario> findAllUsuario() {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		return usuarios;
	}
	
	
}
