package br.edu.ifpb.entidade;

public class Usuario {
	
	private int id;
    private String email;
    private String senha;
    
    public Usuario(){
    	this.email = "";
    	this.senha = "";
    }
    
    public Usuario(String email, String senha){
    	this.email = email;
    	this.senha = senha;
    }
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
