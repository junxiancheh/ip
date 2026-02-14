import org.junit.jupiter.api.Test;
import sleeper.task.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testToDos_onlyNumbers_success() {
        assertEquals("[T][ ] 123", new ToDos("123").toString());
    }

    @Test
    public void testToDos_onlyStrings_success() {
        assertEquals("[T][ ] Hello, World!", new ToDos("Hello, World!").toString());
    }

    @Test
    public void testToDos_onlySpecialCharacters_success() {
        assertEquals("[T][ ] @#$%^&*()", new ToDos("@#$%^&*()").toString());
    }

    @Test 
    public void testToDos_emptyDescription_success() {
        assertEquals("[T][ ] ", new ToDos("").toString());
    }

}