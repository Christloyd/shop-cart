package com.exercice.entity;


/**
 * Represente le product
 */
public interface IProductEntity extends IEntity{
	
	/**
	 * Recupere la propriete <i>utilisateurId</i>.
	 *
	 * @return the utilisateurId la valeur de la propriete.
	 */
	public abstract Integer getProductId();

	/**
	 * Fixe la propriete <i>utilisateurId</i>.
	 *
	 * @param pUtilisateurId
	 *            la nouvelle valeur pour la propriete utilisateurId.
	 */
	public abstract void setProductId(Integer pUtilisateurId);
	
	public abstract String getName();
	
	public abstract Double getPrice();
	
	public abstract void setName(String name);
	
	public abstract void setPrice(Double price);
}
