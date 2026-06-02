package com.bookmyvenue.backend.dao;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryResponse;
import com.bookmyvenue.backend.entity.VenueCategory;
import java.util.List;
import java.util.Optional;

public interface VenueCategoryDao {

    VenueCategoryResponse save(VenueCategoryRequest request);

    Optional<VenueCategoryResponse> findById(Long categoryId);

    List<VenueCategoryResponse> findAll();

    VenueCategoryResponse update(Long categoryId, VenueCategoryRequest request);

    void deleteById(Long categoryId);

    Optional<VenueCategory> findByCategoryName(String categoryName);

    List<VenueCategoryResponse> findAllActiveCategoriesDto();
}