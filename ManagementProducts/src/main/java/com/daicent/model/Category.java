package com.daicent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Comparable<Category>{
	
	private int idCategory;
	private String nameCategory;
	
	@Override
	public int compareTo(Category object) {
		String thisName= this.getNameCategory();
		String objectName= object.getNameCategory();
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
		Category other = (Category) obj;
		if (idCategory != other.idCategory)
			return false;
		if (nameCategory == null) {
			if (other.nameCategory != null)
				return false;
		} else if (!nameCategory.equals(other.nameCategory))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategory;
		result = prime * result + ((nameCategory == null) ? 0 : nameCategory.hashCode());
		return result;
	}
	@Override
	public String toString() {
		String s= String.format("|%-10d%5s", idCategory,nameCategory) ;
		return s;
	}
	
	
}
