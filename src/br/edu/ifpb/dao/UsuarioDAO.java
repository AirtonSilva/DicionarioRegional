package br.edu.ifpb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.entidade.Usuario;

public class UsuarioDAO implements GenericDAO<Integer, Usuario>{

	static ConexaoDAO banco;
	
	public Connection connection;

	private List<Usuario> usuarios;

	private ResultSet rs;
	
	private static UsuarioDAO instance;

	public static UsuarioDAO getInstance() {
		
		banco = ConexaoDAO.getInstance();
		
		instance = new UsuarioDAO(banco);
		
		return instance;
	}
	
	public UsuarioDAO(ConexaoDAO banco) {
		this.connection = (Connection) banco.getConnection();
	}

	public UsuarioDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(Usuario usuario) throws SQLException {
		
		Integer id = BancoDadosDAO.IDVAZIO;
		
		try {
			
			// Considerar a tabela tb_pessoa composta dos campos: id (int) e nome (varchar).
			String sql = "INSERT INTO tb_pessoa (" 
					+ " id_pessoa,"
					+ " nm_pessoa)"
					+ " VALUES (?, ?)";
			
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getId());
			stmt.setString(2, usuario.getEmail());
			
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			// Cadastra e recuperar identificação da Pessoa.
			id = BancoDadosDAO.getGenerateKey(stmt);
		
		} catch (SQLException e) {
			
			connection.close();	
		}
		
		return id;
	}

	@Override
	public Usuario getById(Integer id) throws SQLException {
		
		Usuario usuario = null;

		PreparedStatement stmt = null;
		
		ResultSet rs = null;

		try {

			// Considerar a tabela tb_pessoa composta dos campos: id (int) e nome (varchar).
			String sql = "SELECT pessoa.id, pessoa.nome"
					+ "FROM tb_pessoa AS pessoa"
					+ "WHERE pessoa.id = " 
					+ id;

			stmt = (PreparedStatement) connection.prepareStatement(sql);

			rs = stmt.executeQuery(sql);

			List<Usuario> usuarios = convertToList(rs);

			if (!usuarios.isEmpty())
				usuario = usuarios.get(0);

		} catch (SQLException sqle) {
			
			throw sqle;
			
		} finally {

			connection.close();
		}

		return usuario;
	}

	private List<Usuario> convertToList(ResultSet rs) throws SQLException {
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {

			while (rs.next()) {

				// Usuario
				Usuario usuario = new Usuario();

				usuario.setId(rs.getInt("pessoa.id_pessoa"));
				usuario.setEmail(rs.getString("pessoa.nm_pessoa"));
				
				usuarios.add(usuario);
			}

		} catch (SQLException sqle) {
			
			throw sqle;
		}

		return usuarios;
	}
	
	@Override
	public void update(Usuario entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Integer pk) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Usuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Usuario> find(Usuario entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean consultaUser(Usuario user) {

		banco.getConnection();

		System.out.print("Consulta SQL" + user.getEmail() + " "
				+ user.getSenha());

		String sql = "SELECT u.nome " + "FROM usuario u " + "WHERE u.email='"
				+ user.getEmail() + "'" + "AND u.senha='" + user.getSenha()
				+ "'";

		int aux = 0;
		try {
			Statement st = ((Connection) GenericDAO.connection).createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				if (rs == null) {
					aux = 0;
				} else {
					aux = 1;
				}
			}
			st.close();

		} catch (SQLException sqle) {
			System.out.println("Nao foi possivel realizar consulta");
			sqle.printStackTrace(System.err);
		} catch (NullPointerException npe){
			System.out.println("Nao foi possivel realizar inserção");
			npe.printStackTrace(System.err);
		}

		banco.fecharConexao();

		if (aux == 0) {
			return false;
		} else {
			return true;
		}
	}
}