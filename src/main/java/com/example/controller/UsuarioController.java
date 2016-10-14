package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Usuario;
import com.example.service.UsuarioServiceImpl;

@RestController
public class UsuarioController {

	static final Logger logger = LogManager.getLogger(UsuarioController.class.getName());

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	//------------------- FIND ALL--------------------------------------------------------
	
	@RequestMapping(value="/usuario/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listAllUsers(){
		List<Usuario> usuarios = usuarioService.findAllUsuario();
		
		if(usuarios.isEmpty()){
			logger.warn("Tabla usuarios VACIA" );
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	//------------------- FIND USER--------------------------------------------------------
	
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") int id){
		Usuario u = usuarioService.findUsuarioById(id);
		if(u == null){
			logger.warn("Usuario con id "+ id + " NO ENCONTRADO" );
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
	
	//------------------- CREATE--------------------------------------------------------
	
	@RequestMapping(value = "/usuario/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
		
		if (usuarioService.findUsuarioById(usuario.getIdUsuario()) != null) {
			logger.warn("Usuario con nombre " + usuario.getName() + " e id " + usuario.getIdUsuario() +" YA EXISTE");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		usuarioService.createUsuario(usuario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(usuario.getIdUsuario()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//------------------- UPDATE--------------------------------------------------------
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Usuario currentUser = usuarioService.findUsuarioById(id);
         
        if (currentUser==null) {
        	logger.warn("Usuario con id " + id + " NO ENCONTRADO");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(usuario.getName());
        currentUser.setEdad(usuario.getEdad());
        currentUser.setEmail(usuario.getEmail());
        currentUser.setPassword(usuario.getPassword());
         
        usuarioService.updateUsuario(currentUser);
        return new ResponseEntity<Usuario>(currentUser, HttpStatus.OK);
    }
	
	//------------------- DELETE--------------------------------------------------------
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteUser(@PathVariable("id") int id) {
		Usuario u = usuarioService.findUsuarioById(id);
        if (u == null) {
        	logger.warn("No se puede BORRAR usuario con id " + id + " NO ENCONTRADO");
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
 
        usuarioService.deleteUsuario(u);
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }
	
}
