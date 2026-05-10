package modelo;

public abstract class Usuario{
	
	private String login;
	private String password;
	private String nombre;
	
	public Usuario(String login, String password, String nombre){
		this.login = login;
		this.password = password;
		this.nombre = nombre;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public boolean autenticar(String login, String password){
		return this.login.equals(login) && this.password.equals(password);
	}
}