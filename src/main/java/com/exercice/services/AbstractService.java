package com.exercice.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Abstract service class. <br/>
 * This class serves as a base class for other service classes and provides a logger instance.
 */
public abstract class AbstractService {
    protected Log LOG = LogFactory.getLog(this.getClass());

    /**
     * Constructor for AbstractService. <br/>
     * Initializes the logger instance for the service.
     */
    public AbstractService() {
        super();
    }
}
