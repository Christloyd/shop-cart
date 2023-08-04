package com.exercice.entity;

/**
 * Represents a product entity. <br/>
 * This interface extends the IEntity interface and defines additional properties and methods specific to a product entity.
 */
public interface IProductEntity extends IEntity {

    /**
     * Get the value of the property <i>productId</i>.
     *
     * @return the value of the property productId.
     */
    public abstract Integer getProductId();

    /**
     * Set the value of the property <i>productId</i>.
     *
     * @param pUtilisateurId the new value for the productId property.
     */
    public abstract void setProductId(Integer pUtilisateurId);

    /**
     * Get the name of the product.
     *
     * @return the name of the product.
     */
    public abstract String getName();

    /**
     * Get the price of the product.
     *
     * @return the price of the product.
     */
    public abstract Double getPrice();

    /**
     * Set the name of the product.
     *
     * @param name the new name for the product.
     */
    public abstract void setName(String name);

    /**
     * Set the price of the product.
     *
     * @param price the new price for the product.
     */
    public abstract void setPrice(Double price);
}
