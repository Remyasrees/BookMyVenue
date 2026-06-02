package com.bookmyvenue.backend.dao;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryRequest;
import com.bookmyvenue.backend.dto.venueCategory.VenueCategoryResponse;
import com.bookmyvenue.backend.entity.VenueCategory;
import com.bookmyvenue.backend.mapper.VenueCategoryMapper;
import com.bookmyvenue.backend.repository.VenueCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VenueCategoryDaoImpl implements VenueCategoryDao {

    private final VenueCategoryRepository repository;
    private final VenueCategoryMapper mapper;

    @Override
    public VenueCategoryResponse save(VenueCategoryRequest request) {
        VenueCategory entity = mapper.toEntity(request);
        entity.setCreatedBy("SYSTEM");
        entity.setUpdatedBy("SYSTEM");
        VenueCategory saved = repository.save(entity);
        return mapper.toResponse(saved);
    }


    @Override
    public Optional<VenueCategoryResponse> findById(Long categoryId) {
        return repository.findByIdVenuCategoryDto(categoryId);
    }

    @Override
    public List<VenueCategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

        @Override
    public VenueCategoryResponse update(Long categoryId, VenueCategoryRequest request) {
        VenueCategory existing = repository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        existing.setCategoryName(request.getCategoryName());
        existing.setDescription(request.getDescription());
        existing.setIsActive(request.getIsActive());
        existing.setUpdatedBy("SYSTEM");

        VenueCategory updated = repository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteById(Long categoryId) {
        repository.deleteById(categoryId);
    }

    @Override
    public Optional<VenueCategory> findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName);
    }

    @Override
    public List<VenueCategoryResponse> findAllActiveCategoriesDto() {
        // Delegate to repository which returns DTO projection for active categories
        return repository.findAllActiveVenuCategoriesDto();
    }



}