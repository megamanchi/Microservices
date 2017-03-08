package io.pivotal.microservices.as400;

import com.scotiabank.atr.AtrJava;

public class AdapterAtrJava {

	//ST0140AU -d"0010L000716945407" -c"C:\\AtrJavaTest\\atrjava.properties"
	String config = "c:\\Atrjava\\atrjava.properties";
	
	public AtrJava getAdapterAtrJava()
	{
		return new AtrJava(config);
	}
}
