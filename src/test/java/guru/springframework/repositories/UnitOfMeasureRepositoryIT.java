package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

// Integration Test -> IT
//@RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
//    @DirtiesContext // force Spring Context reload upon method run
    public void findByDescriptionTeaspoon() {
        String measure = "Teaspoon";
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(measure);
        assertEquals(measure, unitOfMeasureOptional.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        String measure = "Cup";
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription(measure);
        assertEquals(measure, unitOfMeasureOptional.get().getDescription());
    }
}