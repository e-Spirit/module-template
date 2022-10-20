package com.espirit.modules.example1;

import com.espirit.modules.example1.dataaccess.plugin.ExampleDataAccessPlugin;
import com.espirit.modules.example1.dataaccess.session.ExampleDataAccessSession;
import com.espirit.modules.example1.dataaccess.datastream.ExampleDataStream;

/**
 * Example Plain Old Java Object
 * This object can carry some information about something.
 *
 * @see ExampleDataAccessPlugin
 * @see ExampleDataAccessSession
 * @see ExampleDataStream
 */
public class ExamplePOJO {

	private final String var;


	/**
	 * Instantiates a new example POJO.
	 *
	 * @param var		     the example's var
	 */
	public ExamplePOJO(String var) {
		this.var = var;
	}

	/**
	 * Gets examples's var.
	 *
	 * @return the var
	 */
	public String getVar() {
		return this.var;
	}

}
