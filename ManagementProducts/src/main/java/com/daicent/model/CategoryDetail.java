package com.daicent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetail implements Comparable<CategoryDetail>{
	private int idCategoryDetail;
	private String nameCategoryDetail;
	private int idCategory;
	@Override
	public int compareTo(CategoryDetail object) {
		String thisName= this.getNameCategoryDetail();
		String objectName= object.getNameCategoryDetail();
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
		CategoryDetail other = (CategoryDetail) obj;
		if (idCategory != other.idCategory)
			return false;
		if (idCategoryDetail != other.idCategoryDetail)
			return false;
		if (nameCategoryDetail == null) {
			if (other.nameCategoryDetail != null)
				return false;
		} else if (!nameCategoryDetail.equals(other.nameCategoryDetail))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategory;
		result = prime * result + idCategoryDetail;
		result = prime * result + ((nameCategoryDetail == null) ? 0 : nameCategoryDetail.hashCode());
		return result;
	}
	@Override
	public String toString() {
		String s= String.format("\t"+"|%-15s%-20s%5s", idCategoryDetail,nameCategoryDetail, idCategory) ;
		return s;
		
	}
	
}
