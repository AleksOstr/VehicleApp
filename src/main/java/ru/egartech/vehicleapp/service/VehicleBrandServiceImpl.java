package ru.egartech.vehicleapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.egartech.vehicleapp.exceptions.ExistingValueException;
import ru.egartech.vehicleapp.model.VehicleBrand;
import ru.egartech.vehicleapp.repository.VehicleBrandRepository;
import ru.egartech.vehicleapp.service.interfaces.VehicleBrandService;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Сервис марок ТС
 */
@Service
@RequiredArgsConstructor
public class VehicleBrandServiceImpl implements VehicleBrandService {

    private final VehicleBrandRepository brandRepository;

    /**
     * Создание новой марки
     * @param brandName - имя марки ТС
     * @return VehicleBrand - сущность марки ТС
     */
    @Override
    public VehicleBrand create(String brandName) throws ExistingValueException {
        VehicleBrand brand = new VehicleBrand();
        brand.setName(brandName);
        brand.setVehicles(new ArrayList<>());
        brand.setModels(new ArrayList<>());
        return brandRepository.save(brand);
    }

    /**
     * Поиск марки по имени
     *
     * @param brandName - имя марки ТС
     * @return VehicleBrand - сущность марки ТС
     */
    @Override
    public Optional<VehicleBrand> findByName(String brandName) {
        return brandRepository.findByNameIgnoreCase(brandName);
    }

    /**
     * Обновление марки в репозитории
     * @param brand - сущность марки ТС
     */
    @Override
    public VehicleBrand update(VehicleBrand brand) {
        return brandRepository.save(brand);
    }

}
