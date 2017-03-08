package io.pivotal.microservices.as400;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.pivotal.microservices.accounts.AccountsController;


public class CuentaParser extends BaseParser {

	public java.lang.Long getNumCta() {
		return numCta;
	}
	public void setNumCta(java.lang.Long numCta) {
		this.numCta = numCta;
	}
	public java.lang.String getMoneda() {
		return moneda;
	}
	public void setMoneda(java.lang.String moneda) {
		this.moneda = moneda;
	}
	public java.lang.String getNombre() {
		return nombre;
	}
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}
	public java.lang.Long getSaldoDisp() {
		return saldoDisp;
	}
	public void setSaldoDisp(java.lang.Long saldoDisp) {
		this.saldoDisp = saldoDisp;
	}
	@Override
	public <Any> Any traslateOutput()
	{
		//logger.info("Traslate CuentaParser");
		numCta= java.lang.Long.parseLong(findProperty(0,11));
		//logger.info(numCta.toString());
		moneda = findProperty(12,9);		
		//logger.info( moneda.toString());
		nombre = findProperty(20,40);		
		//logger.info(nombre);
		saldoDisp = java.lang.Long.parseLong(findProperty(94,15));
		//logger.info(saldoDisp.toString());
		
		return (Any) this;
				
	}
	@Override
	public void traslateInput(String cta)
	{
		String prefijocta="0010L";
		int lengthCta=12;
		String ceros=""; 
		if (cta.length()<lengthCta)
		{
			int dif= lengthCta-cta.length();
			for ( int i = 0; i < dif; i ++ ) {
				ceros= ceros.concat("0");
			}		
		}
		this.setNumCtaAs400(ceros.concat(cta)); 
		this.setInput(prefijocta.concat(this.getNumCtaAs400()));			
	}
	@JsonProperty("numCta")
	private java.lang.Long numCta;
	@JsonProperty("numCtaAs400")
	private java.lang.String numCtaAs400;
	public java.lang.String getNumCtaAs400() {
		return numCtaAs400;
	}
	public void setNumCtaAs400(java.lang.String numCtaAs400) {
		this.numCtaAs400 = numCtaAs400;
	}
	@JsonProperty("moneda")
	private java.lang.String moneda;
	@JsonProperty("nombre")
	private java.lang.String nombre;
	@JsonProperty("saldoDisp")
//	@JsonIgnore
	private java.lang.Long saldoDisp;
}
