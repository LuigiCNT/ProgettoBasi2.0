package it.unirc.twd.beans;

public class Utente {
private String Username;
private String Password;
private int Autorita;
public Utente(String username, String password, int autorita) {
	super();
	Username = username;
	Password = password;
	Autorita = autorita;
}
public String getUsername() {
	return Username;
}
public void setUsername(String username) {
	Username = username;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public int getAutorita() {
	return Autorita;
}
public void setAutorita(int autorita) {
	Autorita = autorita;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Autorita;
	result = prime * result + ((Password == null) ? 0 : Password.hashCode());
	result = prime * result + ((Username == null) ? 0 : Username.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Utente other = (Utente) obj;
	if (Autorita != other.Autorita)
		return false;
	if (Password == null) {
		if (other.Password != null)
			return false;
	} else if (!Password.equals(other.Password))
		return false;
	if (Username == null) {
		if (other.Username != null)
			return false;
	} else if (!Username.equals(other.Username))
		return false;
	return true;
}
public Utente() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Utente [Username=" + Username + ", Password=" + Password + ", Autorita=" + Autorita + "]";
}

}
