package com.web.SellShoes.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.SellShoes.dto.responseDto.CategoryPageResponseDto;
import com.web.SellShoes.dto.responseDto.CategoryResponseDto;
import com.web.SellShoes.entity.Category;
import com.web.SellShoes.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping()
	public String list(Model model) {

		return "admin/categories/add";
	}

	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAllCategory(Model model) {
		List<Category> categories = categoryService.getAll();
		List<CategoryResponseDto> categoryResponseDtos = categories.stream()
				.map(category -> new CategoryResponseDto(category.getId(), category.getCategoryName(),
						category.getCreateAt(),category.getDeleteAt(),category.getUpdateAt()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(categoryResponseDtos);
	}

	@GetMapping("/getCategoryPage")
	public ResponseEntity<CategoryPageResponseDto> getCategoryPage(@RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String keyword // Đọc tham số page từ URL
			) {
		
		  Page<Category> categoryPage = null;
		    if (keyword == null || keyword.isEmpty()) {
		        categoryPage = categoryService.getAllCategory(page, size);
		    } else {
		        categoryPage = categoryService.getCategoryByKey(page, size, keyword);
		    }
		List<CategoryResponseDto> categoryResponseDtos = categoryPage.stream()
				.map(category -> new CategoryResponseDto(category.getId(), category.getCategoryName(),
						category.getCreateAt(),category.getDeleteAt(),category.getUpdateAt()))
				.collect(Collectors.toList());
      CategoryPageResponseDto categoryPageResponseDto=new CategoryPageResponseDto(categoryPage.getTotalPages(),
    		  categoryPage.getNumber(), categoryPage.getSize(),categoryResponseDtos);
		return  ResponseEntity.ok(categoryPageResponseDto);
    //  return ResponseEntity.ok().body("{\"message\": \"Add product successfully\"}"); 
	}

	@RequestMapping("/saveCategory")
	public ResponseEntity<String> saveCategory(@RequestParam(name = "categoryName") String categoryName,
			ModelMap model) {
		Optional<Category> existingCategory = categoryService.findByCategoryName(categoryName);

		if (existingCategory.isPresent()) {
			return ResponseEntity.badRequest().body("Category name already exists");
		}

		Category category = new Category();
		category.setCategoryName(categoryName);
		categoryService.save(category);

		//return ResponseEntity.ok("Success");
		return ResponseEntity.ok().body("{\"message\": \"Add categories successfully\"}"); 
	}

	@GetMapping("/deleteCategory")
	public ResponseEntity<String> deleteCategory(ModelMap model, @RequestParam("idCategory") int idCategory) {

		Optional<Category> entity = categoryService.findById(idCategory);
		if (entity.isPresent()) {
			Category category = entity.get();
			categoryService.delete(category);
			categoryService.save(category);
		}

		return ResponseEntity.ok("Success");
	}

	@PostMapping("/editCategory")
	public ResponseEntity<String> editCategory(ModelMap model, @RequestParam("idCategory") int idCategory,
			@RequestParam("categoryName") String categoryName) {

		LocalDate currentDate = LocalDate.now();
		// Tìm danh mục dựa trên id
		Optional<Category> categoryToUpdate = categoryService.findById(idCategory);
		System.out.println("_-----------" + idCategory);
		if (categoryToUpdate.isPresent()) {
			Category entity = categoryToUpdate.get();
			entity.setId(idCategory);
			entity.setCategoryName(categoryName);
			entity.setUpdateAt(currentDate);
			categoryService.save(entity);

		}
		return ResponseEntity.ok("Success");

	}

}
