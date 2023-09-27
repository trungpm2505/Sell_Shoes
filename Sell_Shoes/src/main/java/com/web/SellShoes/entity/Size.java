package com.web.SellShoes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Size {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, length = 20)
	private String size;
	
	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
	private List<Variant> variants = new ArrayList<>();
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
//	private Product product;
}
