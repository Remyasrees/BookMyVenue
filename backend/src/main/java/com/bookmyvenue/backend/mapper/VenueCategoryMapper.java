package com.bookmyvenue.backend.mapper;


import com.bookmyvenue.backend.dto.request.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.response.VenueCategoryResponse;
import com.bookmyvenue.backend.entity.VenueCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueCategoryMapper {

    VenueCategory toEntity(VenueCategoryRequest request);

    VenueCategoryResponse toResponse(VenueCategory entity);
}