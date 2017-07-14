package br.com.fitnesfood.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitnessfood.persistencia.entidade.Usuario;

public class UsuarioDAO {

	Connection con = ConnectionFactory.getConnection();

	public void cadastrar(Usuario usu) {

		String sql = "insert into usuario(nome, login, senha) values( ?, ?, ? )";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// Substituindo o '?' pelo dado do usuario
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());

			// Executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// String sql = "update into usuario set nome = ?, login = ?, senha = ?
		// where id = ? ";

	}

	public void alterar(Usuario usu) {

		String sql = "update usuario set nome = ?, login = ?, senha = ? where id = ? ";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// Substituindo o '?' pelo dado do usuario
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());

			// Executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usu) {

		String sql = "delete from usuario where id = ? ";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// Substituindo o '?' pelo dado do usuario
			preparador.setInt(1, usu.getId());

			// Executando o comando sql no banco
			preparador.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void salvar(Usuario usuario) {
		 if(usuario.getId() > 0){
		 cadastrar(usuario);
		 }
	}

	/**
	 * Busca de um registro pelo Id do usuario
	 * 
	 * @param id
	 *            é o numerp que represento o numero do id do usuario a ser
	 *            buscado no banco
	 * @return um objeto do tipo Usuario ou Null quando nao encontra nehum Id no
	 *         banco
	 */

	public Usuario buscarPorId(Integer id) {

		String sql = "select * from usuario where id = ?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			if (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSenha(resultado.getString("senha"));

				return usuario;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Realiza a busca de todos os elementos da tabela usuarios
	 * 
	 * @return 0 se nao ouver registros ou 'n' elementos
	 */
	public List<Usuario> buscarTodos() {

		String sql = "select * from usuario ";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {

			ResultSet resultado = preparador.executeQuery();
			// Posicionando cursor no primeiro registro
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setSenha(resultado.getString("senha"));
				// Adicionando usuario na lista
				lista.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public Usuario autenticar(Usuario usuConsulta) {

		String sql = "select * from usuario where login = ? and senha = ?";

		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));

				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
