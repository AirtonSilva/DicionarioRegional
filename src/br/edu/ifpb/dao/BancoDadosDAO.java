package br.edu.ifpb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BancoDadosDAO {

	public static int IDVAZIO = 0;

	public static int NOROWSUPDATED = 0;

	public BancoDadosDAO() {
	}

	public static int getGenerateKey(PreparedStatement stmt) throws SQLException {

		int key = 0;

		// recuperar a chave
		ResultSet rs = stmt.getGeneratedKeys();

		// recuperar a chave como inteiro
		if (rs.next()) {
			key = rs.getInt(1);
		}

		return key;
	}
}