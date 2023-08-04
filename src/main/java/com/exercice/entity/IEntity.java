package com.exercice.entity;
import java.io.Serializable;

/**
 * Represents an entity. <br/>
 * This interface defines a contract for entities in the application.
 */
public interface IEntity extends Serializable {

    /**
     * Get the ID of the entity.
     *
     * @return the ID of the entity.
     */
    public abstract Integer getId();

    /**
     * Set the ID of the entity.
     *
     * @param unId the ID of the entity.
     */
    public abstract void setId(Integer unId);

}

