package com.exercice.controllers;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 * Base Controller. <br/>
 * This class serves as the base controller for other controllers in the application.
 */
@Controller
public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = 1L;

    // Logger for logging messages and events in the application.
    protected Log LOG = LogFactory.getLog(this.getClass());

    /**
     * Constructor of the object.
     * Protected constructor to be used by child classes that extend this abstract controller.
     */
    protected AbstractController() {
        super();
    }

}


