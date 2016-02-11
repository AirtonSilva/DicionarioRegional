package br.edu.ifpb.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	
	private static ConexaoDAO conect;
	
	public static ConexaoDAO getInstance() {
		
		if (conect == null)
			conect = new ConexaoDAO();
		
		return conect;
	}
	
	public Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			// Endereço do Banco, Login e Senha. Substituir: [BANCO], [USUARIO], [SENHA]
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/[BANCO]", 
					"[EMAIL]", 
					"[SENHA]");
			
		} catch (SQLException | ClassNotFoundException e) {
			
			throw new RuntimeException(e);
		}
	}

	public void fecharConexao() {
		// TODO Auto-generated method stub
		
	}
}