package io.pivotal.microservices.as400;

import java.util.logging.Logger;

import io.pivotal.microservices.accounts.Account;

public class BaseParser implements Iparser {
	protected Logger logger = Logger.getLogger(CuentaParser.class
			.getName());
	private String output;
	public String getNombreAplicacion() {
		return nombreAplicacion;
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	private String nombreAplicacion ="SRVINTER";
	private String nombreServicio="ST0140AU";
	private String nombreUsuario = "MICROSERVICECUENTA";
	private int timeout=30;
	
	
	
	
	/* (non-Javadoc)
	 * @see io.pivotal.microservices.as4400parser.Iparser2#getOutput()
	 */
	@Override
	public String getOutput() {
		return output;
	}
	/* (non-Javadoc)
	 * @see io.pivotal.microservices.as4400parser.Iparser2#setOutput(java.lang.String)
	 */
	@Override
	public void setOutput(String output) {
		this.output = output;
	}
	/* (non-Javadoc)
	 * @see io.pivotal.microservices.as4400parser.Iparser2#getInput()
	 */
	@Override
	public String getInput() {
		return input;
	}
	/* (non-Javadoc)
	 * @see io.pivotal.microservices.as4400parser.Iparser2#setInput(java.lang.String)
	 */
	@Override
	public void setInput(String input) {
		this.input = input;
	}
	private String input;
	
	public <Any> Any traslateOutput() {
		logger.info("Traslate base Parser");
		// TODO Auto-generated method stub
		return null;
	}
	public String findProperty(int Position,int Lentgh)
	{		
		int endIndex = Position+Lentgh;
		return  output.substring(Position,endIndex);
		
	}

	public void traslateInput(String cta) {
		logger.info("formatInput base");
		// TODO Auto-generated method stub
		
	}
}
