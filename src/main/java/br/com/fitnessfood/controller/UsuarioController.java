package br.com.fitnessfood.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitnesfood.persistencia.jdbc.UsuarioDAO;
import br.com.fitnessfood.persistencia.entidade.Usuario;

@WebServlet ("/usucontroller.do")
public class UsuarioController extends HttpServlet{
	
	public UsuarioController(){
		System.out.println("CConstrutor");
	}
	
	public void init() throws ServletException{
		System.out.println("Init! ");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String acao = req.getParameter("acao");
		if(acao.equals("excluir")){
		String id = req.getParameter("id");
		
		
		//Ecluindo usuario existente
		Usuario usu = new Usuario();
		if (id != null){
			usu.setId(Integer.parseInt(id));
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usu);
			
			resp.getWriter().print("Excluido com sucesso!!!");
		}
		}else if(acao.equals("listar")){
			//implementar lista
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscarTodos();
			for(Usuario u: lista){
				resp.getWriter().print(lista);
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		
		Usuario usu = new Usuario();
		if (id != null){
			usu.setId(Integer.parseInt(id));
			
			
		}
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);
		
		resp.getWriter().print("Dados Salvos com Sucesso!!!");
		System.out.println("Salvo!!!");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
	}
}
