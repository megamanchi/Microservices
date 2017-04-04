package io.pivotal.microservices.as400;


public interface Iparser {
	public <Any> Any  traslateOutput();
	String getOutput();

	void setOutput(String output);
	void traslateInput(String cta);
	String getInput();

	void setInput(String input);
   
	
	public String getNombreAplicacion();
	public void setNombreAplicacion(String nombreAplicacion); 
	public String getNombreServicio(); 
	public void setNombreServicio(String nombreServicio); 
	public String getNombreUsuario(); 
	public void setNombreUsuario(String nombreUsuario);
	public int getTimeout(); 
	public void setTimeout(int timeout); 

}