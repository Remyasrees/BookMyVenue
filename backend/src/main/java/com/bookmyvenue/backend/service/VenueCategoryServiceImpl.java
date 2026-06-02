package com.bookmyvenue.backend.service;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryResponse;
import com.bookmyvenue.backend.exception.ResourceNotFoundException;
import com.bookmyvenue.backend.dao.VenueCategoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueCategoryServiceImpl implements VenueCategoryService {

    private final VenueCategoryDao dao;

    @Override
    public VenueCategoryResponse createCategory(VenueCategoryRequest request) {
        validateCategoryRequest(request);
        checkDuplicateCategoryName(request.getCategoryName());
        return dao.save(request);
    }

    @Override
    public VenueCategoryResponse getCategoryById(Long id) {
        validateId(id);
        return dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<VenueCategoryResponse> getAllCategories() {
        return dao.findAll();
    }

    @Override
    public VenueCategoryResponse updateCategory(Long id, VenueCategoryRequest request) {
        validateId(id);
        validateCategoryRequest(request);
        
        dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        
        return dao.update(id, request);
    }

    @Override
    public void deleteCategory(Long id) {
        validateId(id);
        dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        dao.deleteById(id);
    }

    public List<VenueCategoryResponse> getActiveCategoriesOnly() {
        return dao.findAllActiveCategoriesDto();
    }

    public VenueCategoryResponse getCategoryByName(String categoryName) {
        return dao.findByCategoryName(categoryName)
                .map(entity -> {
                    VenueCategoryResponse response = new VenueCategoryResponse();
                    response.setCategoryId(entity.getCategoryId());
                    response.setCategoryName(entity.getCategoryName());
                    response.setDescription(entity.getDescription());
                    response.setIsActive(entity.getIsActive());
                    response.setCreatedAt(entity.getCreatedAt());
                    response.setUpdatedAt(entity.getUpdatedAt());
                    return response;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with name: " + categoryName));
    }

    private void validateCategoryRequest(VenueCategoryRequest request) {
        if (request.getCategoryName() == null || request.getCategoryName().isBlank()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (request.getCategoryName().length() > 255) {
            throw new IllegalArgumentException("Category name cannot exceed 255 characters");
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid category id");
        }
    }

    private void checkDuplicateCategoryName(String categoryName) {
        dao.findByCategoryName(categoryName).ifPresent(entity -> {
            throw new IllegalArgumentException("Category with name '" + categoryName + "' already exists");
        });
    }
}