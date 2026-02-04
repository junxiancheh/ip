import org.junit.jupiter.api.Test;
import sleeper.task.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testNumbers() {
        assertEquals("[T][ ] 123", new ToDos("123").toString());
    }

    @Test
    public void testStrings() {
        assertEquals("[T][ ] Hello, World!", new ToDos("Hello, World!").toString());
    }

    @Test
    public void testSpecialCharacters() {
        assertEquals("[T][ ] @#$%^&*()", new ToDos("@#$%^&*()").toString());
    }

    @Test 
    public void testEmptyDescription() {
        assertEquals("[T][ ] ", new ToDos("").toString());
    }

}