package com.exercice.controllers;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

/**
 * Controller de base. <br/>
 */
@Controller
public abstract class AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de l'objet.
	 */
	protected AbstractController() {
		super();
	}

}
