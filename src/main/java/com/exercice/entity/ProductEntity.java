package com.exercice.entity;

public class ProductEntity extends AbstractEntity implements IProductEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double price;
	
	private Integer productId;
	
	public ProductEntity() {
		super();
	}
	
	
	public ProductEntity(Integer id, String name, Double price) {
		super(id);
		this.setName(name);
		this.setPrice(price);
	}
	

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
		if((name == null) || (name.trim().length() == 0)) {
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
		if(price >= 0 ) {
			this.price = price;
		}
	}



	@Override
	public String toString() {
		return "ProductEntity [name=" + name + ", price=" + price + "]";
	}
	
	
}
