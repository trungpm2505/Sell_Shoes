package com.web.SellShoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web.SellShoes.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer>{
	@Query("SELECT c FROM Size c WHERE c.deleteAt is null AND c.id = :sizeId")
	public Optional<Size> getSizeById(Integer sizeId);
}
