package com.web.SellShoes.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Color {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(nullable = false, length = 50, columnDefinition = "NVARCHAR(50)")
	private String color;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate createAt = LocalDate.now();

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate updateAt;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate deleteAt;
	
	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
	private List<Variant> variants = new ArrayList<>();
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
//	private Product product;
}
