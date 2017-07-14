package br.com.fitnessfood;

import java.util.List;

import br.com.fitnesfood.persistencia.jdbc.UsuarioDAO;
import br.com.fitnessfood.persistencia.entidade.Usuario;

public class TesteUsuariDAO {
	public static void main(String[] args) {
		//testeCadastrar();
		testeAlterar();
		//testeExcluir();
		//testeSalvar();
		//testeBuscaPorId();
		//testeBuscarTodos();
		//testeAutenticar();
		
	}
	
	private static void testeAutenticar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("marcio");
		usu.setSenha("willian");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usu);
		System.out.println(usuRetorno);
		
	}

	private static void testeBuscaPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(10);
		
		System.out.println(usuario.toString());
		
	}
	
	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		
		for(Usuario u: lista){
			System.out.println(u);
		}
		
	}

	public static void testeExcluir(){
		Usuario usu = new Usuario();
		usu.setId(9);
		
		//Cadastrando usuario no banco
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso!!!");
}
	
	//Alteração de Cadastro dos Usuarios
	public static void testeAlterar(){
				Usuario usu = new Usuario();
				usu.setId(3);
				usu.setNome(" Edilson");
				usu.setCpf("12345678910");
				usu.setEmail("edilson@gmail.com");
				usu.setLogin("edilson");
				usu.setSenha("edilson");
				
				//Cadastrando usuario no banco
				UsuarioDAO usuDAO = new UsuarioDAO();
				usuDAO.alterar(usu);
				
				System.out.println("Alterado com sucesso!!!");
	}
	
	//Cadastro de usuario
	public static void testeCadastrar(){
		//Criando o Usuario
				Usuario usu = new Usuario();
				usu.setNome("Edilson Lima");
				usu.setCpf("");
				usu.setEmail("");
				usu.setLogin("edilson");
				usu.setSenha("edilson");
				
				//Cadastrando usuario no banco
				UsuarioDAO usuDAO = new UsuarioDAO();
				usuDAO.cadastrar(usu);
				
				System.out.println("Cadastrado com sucesso!!!");
	}
	
	
	//Metodo que salva novo usuario ou atualiza se ja existir usuario com id informado
	public static void testeSalvar(){
		Usuario usuario = new Usuario();
		usuario.setId(11);
		usuario.setNome("Michaell Jackson");
		usuario.setCpf("");
		usuario.setEmail("");
		usuario.setLogin("michaell");
		usuario.setSenha("michaell");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		System.out.println("Salvo com Sucesso!!!");
	}
	

}
