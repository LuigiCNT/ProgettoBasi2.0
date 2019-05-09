package it.unirc.twd.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import it.unirc.twd.utils.DBManager;

public class UtenteDAO {
	private static Connection conn = null;

	public Utente getUtente(Utente ut) {
		String query = "Select * FROM Utente where username = ?";
		Utente res = null;
		PreparedStatement ps;
		conn=DBManager.startConnection();
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, ut.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res=new Utente();
				res.setUsername(rs.getString("username"));
				res.setPassword(rs.getString("password"));
				res.setAutorita(rs.getBoolean("autorità"));
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean SalvaUtente(Utente ut) {
		String query = "INSERT INTO Utente VALUES (?, ?, ?)";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ut.getUsername());
			ps.setString(2, ut.getPassword());
			ps.setBoolean(3, ut.isAutorita());
			int tmp=ps.executeUpdate();
			if(tmp==1) {
				esito=true;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	public boolean EliminaUtente(Utente ut) {
		String query = "DELETE * FROM Utente WHERE Username = ?";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ut.getUsername());
			int tmp=ps.executeUpdate();
			if(tmp==1) {
				esito=true;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	public boolean AggiornaUtente(Utente ut) {
		String query = "UPDATE Utente SET Password = ?, autorità = ? WHERE Username = ?";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ut.getPassword());
			ps.setBoolean(2, ut.isAutorita());
			ps.setString(3, ut.getUsername());
			int tmp=ps.executeUpdate();
			if(tmp==1) {
				esito=true;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public Vector<Utente> getAll(){
		String query = "SELECT * FROM Utente";
		Vector<Utente> list = new Vector<Utente>();
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(recordToUtente(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return list;
	}

	protected Utente recordToUtente(ResultSet rs) throws SQLException {
		Utente res=new Utente();
		res.setUsername(rs.getString("Username"));
		res.setPassword(rs.getString("Password"));

		res.setAutorita(rs.getBoolean("Autorità"));
		return res;

	}
	public boolean login(Utente u) {
		String query="SELECT * FROM Utente WHERE Username = ? AND Password = ?";
		boolean esito=false;
		conn=DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			int tmp=ps.executeUpdate();
			if(tmp!=0) {
				esito=true;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}
