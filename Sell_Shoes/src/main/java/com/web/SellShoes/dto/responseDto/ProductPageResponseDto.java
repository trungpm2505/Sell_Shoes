package com.web.SellShoes.dto.responseDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPageResponseDto {
	private int totalPages;
	private int currentPage;
	private int size;
	private List<ProductResponseDto> productResponseDtos;
}
