package com.exercice.entity;

/**
 * Represents a Product entity. <br/>
 * This class extends the AbstractEntity class and implements the IProductEntity interface.
 * It represents a product with properties such as name, price, and product ID.
 */
public class ProductEntity extends AbstractEntity implements IProductEntity {
    
    private static final long serialVersionUID = 1L;
    
    // Properties specific to ProductEntity
    private String name;
    private Double price;
    private Integer productId;
    
    /**
     * Default constructor for ProductEntity.
     * Initializes the properties with default values.
     */
    public ProductEntity() {
        super();
    }
    
    /**
     * Constructor for ProductEntity with parameters.
     * Initializes the ID, name, and price of the product.
     *
     * @param id    the ID of the product
     * @param name  the name of the product
     * @param price the price of the product
     */
    public ProductEntity(Integer id, String name, Double price) {
        super(id);
        this.setName(name);
        this.setPrice(price);
    }
    
    // Implementation of methods from IProductEntity interface
    
    @Override
    public Integer getProductId() {
        return this.productId;
    }
    
    @Override
    public void setProductId(Integer pProductId) {
        this.productId = pProductId;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        // Set the name if it is not empty or null, otherwise set it to null.
        if ((name == null) || (name.trim().length() == 0)) {
            name = null;
        } else {
            this.name = name;
        }
    }
    
    @Override
    public Double getPrice() {
        return price;
    }
    
    @Override
    public void setPrice(Double price) {
        // Set the price if it is non-negative, otherwise do not change the value.
        if (price >= 0) {
            this.price = price;
        }
    }
    
    @Override
    public String toString() {
        return "ProductEntity [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
    
}
