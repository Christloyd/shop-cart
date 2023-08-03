package com.exercice.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractService {
	protected Log LOG = LogFactory.getLog(this.getClass());

	/**
	 * Constructeur de l'objet.
	 */
	public AbstractService() {
		super();
	}
}
