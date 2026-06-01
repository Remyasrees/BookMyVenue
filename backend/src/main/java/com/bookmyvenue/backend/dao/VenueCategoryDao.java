package com.bookmyvenue.backend.dao;

import com.bookmyvenue.backend.dto.request.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.response.VenueCategoryResponse;
import com.bookmyvenue.backend.entity.VenueCategory;

import java.util.List;
import java.util.Optional;

public interface VenueCategoryDao {

    VenueCategoryResponse save(VenueCategoryRequest request);

    Optional<VenueCategoryResponse> findById(Long categoryId);

    List<VenueCategoryResponse> findAll();

    VenueCategoryResponse update(Long categoryId, VenueCategoryRequest request);

    void deleteById(Long categoryId);

    VenueCategory saveEntity(VenueCategory venueCategory);

    Optional<VenueCategory> findEntityById(Long categoryId);

    Optional<VenueCategory> findByCategoryName(String categoryName);

    List<VenueCategory> findByIsActive(Boolean isActive);

    Optional<VenueCategoryResponse> findByIdDto(Long categoryId);

    List<VenueCategoryResponse> findAllDto();

    List<VenueCategoryResponse> findAllActiveCategoriesDto();
}