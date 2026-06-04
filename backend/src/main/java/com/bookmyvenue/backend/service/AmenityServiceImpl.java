
package com.bookmyvenue.backend.service;
import com.bookmyvenue.backend.dto.venueAmenity.AmenityRequest;
import com.bookmyvenue.backend.dto.venueAmenity.AmenityResponse;
import com.bookmyvenue.backend.entity.Amenity;
import com.bookmyvenue.backend.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    @Override
   public AmenityResponse createAmenity(AmenityRequest request)
              {
                  Amenity amenity = Amenity.builder()
                .amenityName(request.getAmenityName())
                .description(request.getDescription())
                .isActive(request.getIsActive())
                .createdBy(1L)
                .updatedBy(1L)
                .build();

        Amenity savedAmenity = amenityRepository.save(amenity);

        return mapToResponse(savedAmenity);
    }

    @Override
    public AmenityResponse getAmenityById(Long id) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Amenity not found"));

        return mapToResponse(amenity);
    }

    @Override
    public List<AmenityResponse> getAllAmenities() {

        return amenityRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public AmenityResponse updateAmenity(Long id,
                                            AmenityRequest request) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Amenity not found"));

        amenity.setAmenityName(request.getAmenityName());
        amenity.setDescription(request.getDescription());
        amenity.setIsActive(request.getIsActive());
        amenity.setUpdatedBy(1L);

        Amenity updatedAmenity =
                amenityRepository.save(amenity);

        return mapToResponse(updatedAmenity);
    }

    @Override
    public void deleteAmenity(Long id) {

        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Amenity not found"));

        amenityRepository.delete(amenity);
    }

    private AmenityResponse mapToResponse(Amenity amenity) {

        return AmenityResponse.builder()
                .amenityId(amenity.getAmenityId())
                .amenityName(amenity.getAmenityName())
                .description(amenity.getDescription())
                .isActive(amenity.getIsActive())
                .createdAt(amenity.getCreatedAt())
                .updatedAt(amenity.getUpdatedAt())
                .createdBy(amenity.getCreatedBy())
                .updatedBy(amenity.getUpdatedBy())
                .build();
    }
}