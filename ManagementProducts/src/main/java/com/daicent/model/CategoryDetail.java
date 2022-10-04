package com.daicent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetail {
	private int idCategoryDetail;
	private String nameCategoryDetail;
	private int idCategory;
}
