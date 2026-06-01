package com.bookmyvenue.backend.repository;
import com.bookmyvenue.backend.dto.response.VenueCategoryResponse;
import com.bookmyvenue.backend.entity.VenueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VenueCategoryRepository extends JpaRepository<VenueCategory, Long> {

    Optional<VenueCategory> findByCategoryName(String categoryName);

    List<VenueCategory> findByIsActive(Boolean isActive);

    @Query("SELECT new com.bookmyvenue.backend.dto.response.VenueCategoryResponse(" +
            "c.categoryId, c.categoryName, c.description, c.isActive, c.createdAt, c.updatedAt) " +
            "FROM VenueCategory c WHERE c.categoryId = :categoryId")
    Optional<VenueCategoryResponse> findByIdDto(@Param("categoryId") Long categoryId);

    @Query("SELECT new com.bookmyvenue.backend.dto.response.VenueCategoryResponse(" +
            "c.categoryId, c.categoryName, c.description, c.isActive, c.createdAt, c.updatedAt) " +
            "FROM VenueCategory c ORDER BY c.categoryId")
    List<VenueCategoryResponse> findAllDto();

    @Query("SELECT new com.bookmyvenue.backend.dto.response.VenueCategoryResponse(" +
            "c.categoryId, c.categoryName, c.description, c.isActive, c.createdAt, c.updatedAt) " +
            "FROM VenueCategory c WHERE c.isActive = true ORDER BY c.categoryName")
    List<VenueCategoryResponse> findAllActiveCategoriesDto();
}
