package com.bookmyvenue.backend.service;
import com.bookmyvenue.backend.dto.request.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.response.VenueCategoryResponse;
import java.util.List;

public interface VenueCategoryService {

    VenueCategoryResponse createCategory(
            VenueCategoryRequest request);

    VenueCategoryResponse getCategoryById(Long id);

    List<VenueCategoryResponse> getAllCategories();

    VenueCategoryResponse updateCategory(
            Long id,
            VenueCategoryRequest request);

    void deleteCategory(Long id);

    VenueCategoryResponse getCategoryByName(String categoryName);

    List<VenueCategoryResponse> getActiveCategoriesOnly();
}
