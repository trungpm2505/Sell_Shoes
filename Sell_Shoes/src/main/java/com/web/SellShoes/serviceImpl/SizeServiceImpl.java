package com.web.SellShoes.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.SellShoes.entity.Size;
import com.web.SellShoes.repository.SizeRepository;
import com.web.SellShoes.service.SizeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
	private final SizeRepository sizeRepository;

	@Override
	public List<Size> getAll() {
		List<Size> sizes = sizeRepository.findAll();
		return sizes;
	}

	@Override
	public Optional<Size> getSize(Integer sizeId) {
		Optional<Size> size = sizeRepository.getSizeById(sizeId);
		return size;
	}

}
