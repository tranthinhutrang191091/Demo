package com.daicent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	private int idProducts;
	private String nameProducts;
	private double price;
	private int amount;
	private int idCategoryDetail;
	
}
