package ru.egartech.vehicleapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.egartech.vehicleapp.exceptions.ExistingValueException;
import ru.egartech.vehicleapp.exceptions.ValueNotFoundException;
import ru.egartech.vehicleapp.model.VehicleType;
import ru.egartech.vehicleapp.repository.VehicleTypeRepository;
import ru.egartech.vehicleapp.service.interfaces.VehicleTypeService;
import ru.egartech.vehicleapp.service.response.VehicleTypeResponse;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class VehicleTypeServiceTest {

    @Autowired
    private VehicleTypeRepository repository;

    @Autowired
    private VehicleTypeService service;

    @Test
    void createTest() {
        String expectedName = "type";

        VehicleType actual = service.create(expectedName);

        Assertions.assertNotNull(actual.getId());
        Assertions.assertEquals(expectedName, actual.getName());
    }

    @Test
    void createExceptionTest() {
        String typeName = "name";
        VehicleType type = service.create(typeName);

        Exception exception = Assertions.assertThrows(ExistingValueException.class, () -> service.create(typeName));
        String message = exception.getMessage();

        Assertions.assertEquals("Type with name: name already exists", message);
    }

    @Test
    void findByNameTest() {
        String name = "name";
        VehicleType type = service.create(name);

        VehicleType found = service.findByName(name);

        Assertions.assertEquals(type.getId(), found.getId());
        Assertions.assertEquals(type.getName(), found.getName());
    }

    @Test
    void findByNameExceptionTest() {
        Exception exception = Assertions.assertThrows(ValueNotFoundException.class, () -> service.findByName("name"));
        String message = exception.getMessage();

        Assertions.assertEquals("Type with name: name not found", message);
    }

    @Test
    void findAllTest() {
        service.create("1");
        service.create("2");
        service.create("3");

        List<VehicleTypeResponse> responses = service.findAll();
        Assertions.assertNotNull(responses);
        Assertions.assertEquals(3, responses.size());
    }

    @Test
    void updateTest() {
        String oldName = "old name";
        VehicleType saved = service.create(oldName);
        saved.setName("new name");
        VehicleType updated = service.updateType(saved);

        Assertions.assertEquals(saved.getId(), updated.getId());
        Assertions.assertEquals(saved.getName(), updated.getName());
        Assertions.assertNotEquals(oldName, updated.getName());
    }

    @BeforeEach
    void cleanDB() {
        repository.deleteAll();
    }
}