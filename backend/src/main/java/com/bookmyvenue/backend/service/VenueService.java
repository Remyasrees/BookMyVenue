package com.bookmyvenue.backend.service;

import com.bookmyvenue.backend.dto.Venue.VenueRequest;
import com.bookmyvenue.backend.dto.Venue.VenueResponse;
import com.bookmyvenue.backend.enums.VenueStatus;

import java.util.List;

public interface VenueService {

    VenueResponse createVenue(VenueRequest request);

    VenueResponse getVenueById(Long venueId);

    List<VenueResponse> getAllVenues();

    List<VenueResponse> getVenuesByOwner(Long ownerId);

    List<VenueResponse> getVenuesByStatus(VenueStatus status);

    VenueResponse updateVenue(Long venueId,
                              VenueRequest request);

    void deleteVenue(Long venueId);
}