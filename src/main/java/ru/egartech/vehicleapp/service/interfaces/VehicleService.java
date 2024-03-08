package ru.egartech.vehicleapp.service.interfaces;

import ru.egartech.vehicleapp.api.request.VehicleRequest;
import ru.egartech.vehicleapp.service.response.VehicleResponse;
import ru.egartech.vehicleapp.service.response.VehicleTypeResponse;

import java.util.List;
import java.util.UUID;

public interface VehicleService {

    VehicleResponse create(VehicleRequest request);

    VehicleResponse update(VehicleRequest request, UUID id);

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByRequest(VehicleRequest request);

    List<VehicleTypeResponse> getTypes();
}
