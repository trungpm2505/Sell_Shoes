package com.web.SellShoes.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.web.SellShoes.dto.responseDto.ImageResponseDto;
import com.web.SellShoes.entity.Image;
import com.web.SellShoes.entity.Product;


public interface ImageService {
	public void save(Image image);

	public void uploadFile(MultipartFile[] files, int defaultFileIndex, Product product);
	
	public void uploadFileList(List<String> files, int defaultFileIndex, Product product);

	public List<ImageResponseDto> getImageByProduct(Product product);

	public void deleteByProduct(Product product);
}
