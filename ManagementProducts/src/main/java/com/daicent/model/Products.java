package com.daicent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products implements Comparable<Products>{
	private int idProducts;
	private String nameProducts;
	private double price;
	private int amount;
	private int idCategoryDetail;
	@Override
	public int compareTo(Products object) {
		String thisName= this.getNameProducts();
		String objectName= object.getNameProducts();
		return thisName.compareTo(objectName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (amount != other.amount)
			return false;
		if (idCategoryDetail != other.idCategoryDetail)
			return false;
		if (idProducts != other.idProducts)
			return false;
		if (nameProducts == null) {
			if (other.nameProducts != null)
				return false;
		} else if (!nameProducts.equals(other.nameProducts))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + idCategoryDetail;
		result = prime * result + idProducts;
		result = prime * result + ((nameProducts == null) ? 0 : nameProducts.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public String toString() {
		String s= String.format("\t\t"+"|%-10s%-50s%15.0f%10d%10d|", idProducts,nameProducts, price,amount,idCategoryDetail) ;
		return s;
	}
	
}
