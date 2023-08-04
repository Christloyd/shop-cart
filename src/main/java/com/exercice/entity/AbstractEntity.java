package com.exercice.entity;

import java.io.Serializable;

/**
 * Abstract Entity. <br/>
 * This abstract class serves as a base for other entity classes in the application.
 */
public abstract class AbstractEntity implements Serializable, IEntity {

    private static final long serialVersionUID = 1L;

    // The unique identifier for the entity.
    protected Integer id;

    /**
     * Constructor of the object. <br/>
     * Default constructor without any parameters.
     */
    public AbstractEntity() {
        super();
    }

    /**
     * Constructor of the object. <br/>
     * Constructor with a parameter to set the entity's ID.
     *
     * @param unId the ID of the entity
     */
    public AbstractEntity(Integer unId) {
        super();
        this.setId(unId);
    }

    @Override
    public final Integer getId() {
        return this.id;
    }

    @Override
    public final void setId(Integer unId) {
        this.id = unId;
    }

    @Override
    public int hashCode() {
        // Generate a hash code using the entity's class name and ID, if available.
        if (this.getId() != null) {
            return (this.getClass().getName() + "-" + this.getId()).hashCode();
        }
        // If the ID is not available, use the default hash code implementation.
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is null or equal to itself (reference comparison).
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        // Check if the object is an instance of AbstractEntity and compare their IDs for equality.
        if (obj instanceof AbstractEntity) {
            return (((IEntity) obj).getId() == this.getId())
                    || ((IEntity) obj).getId().equals(this.getId());
        }

        // For any other object types, use the default equals method implementation.
        return super.equals(obj);
    }

    @Override
    public String toString() {
        // Generate a string representation of the entity with its class name and ID.
        StringBuffer sb = new StringBuffer();
        sb.append("{class=");
        sb.append(this.getClass().getName());
        sb.append(",id=");
        sb.append(this.getId());
        sb.append('}');
        return sb.toString();
    }
}
