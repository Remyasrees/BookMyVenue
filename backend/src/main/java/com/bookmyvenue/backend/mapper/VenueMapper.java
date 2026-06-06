package com.bookmyvenue.backend.mapper;
import com.bookmyvenue.backend.dto.Venue.VenueRequest;
import com.bookmyvenue.backend.dto.Venue.VenueResponse;
import com.bookmyvenue.backend.entity.Venue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {

    Venue toEntity(VenueRequest request );

    VenueResponse toResponse(Venue response);
}