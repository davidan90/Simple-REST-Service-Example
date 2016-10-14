package com.example.service;

import java.util.List;
import com.example.model.Usuario;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.*;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {
	 
	private Usuario usuario = new Usuario("Prueba", "asdfg", 99, "prueba@mail.es");
	
	 @Autowired
	 UsuarioServiceImpl usuarioService;
	 
	 @Test
	 public void listAllUserTest(){
		 List<Usuario> listaUsuarios = usuarioService.findAllUsuario();
		 Assert.assertNotNull(listaUsuarios);
	 }
	 
	 @Test
	 public void createUserTest(){
		 usuarioService.createUsuario(usuario);
		 showDB();
	 }
	 
	 @Test
	 public void updateUserTest(){
		 Usuario u2 = new Usuario("Prueba", "1234", 00, "prueba@mail.es");
		 usuarioService.updateUsuario(u2);
		 showDB();
	 }
	 
	 @Test
	 public void deleteUserTest(){
		 Usuario u = usuarioService.findUsuarioByEmail(usuario.getEmail());
		 usuarioService.deleteUsuario(u);
		 showDB();
	 }
	 
	 
	 private void showDB(){
		 List<Usuario> listaUsuarios = usuarioService.findAllUsuario();
		 for(Usuario u: listaUsuarios){
			 System.out.println(u.toString());
		 }
	 }
}