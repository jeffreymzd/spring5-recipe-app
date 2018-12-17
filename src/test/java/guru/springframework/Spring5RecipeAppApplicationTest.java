package guru.springframework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
//@RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class)
public class Spring5RecipeAppApplicationTest {

    @Test
    public void main() {
    }
}