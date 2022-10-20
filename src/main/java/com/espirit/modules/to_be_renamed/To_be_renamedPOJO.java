package com.espirit.modules.to_be_renamed;

import com.espirit.modules.to_be_renamed.dataaccess.plugin.To_be_renamedDataAccessPlugin;
import com.espirit.modules.to_be_renamed.dataaccess.session.To_be_renamedDataAccessSession;
import com.espirit.modules.to_be_renamed.dataaccess.datastream.To_be_renamedDataStream;

/**
 * Plain Old Java Object
 * This object can carry some information about something.
 *
 * @see To_be_renamedDataAccessPlugin
 * @see To_be_renamedDataAccessSession
 * @see To_be_renamedDataStream
 */
public class To_be_renamedPOJO {

	private final String var;


	/**
	 * Instantiates a new POJO.
	 *
	 * @param var		     the pojo's var
	 */
	public To_be_renamedPOJO(String var) {
		this.var = var;
	}

	/**
	 * Gets pojo's var.
	 *
	 * @return the var
	 */
	public String getVar() {
		return this.var;
	}

}
