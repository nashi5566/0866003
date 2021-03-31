import java.util.PriorityQueue;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    /* integer test case */
    public static Stream<Arguments> integerStreamProvider(){
        return Stream.of(
                Arguments.of(new int[]{4, 5, 3, 7, 9}, new int[]{3, 4, 5, 7, 9}),
                Arguments.of(new int[]{-1, 4, 5, -10, 2}, new int[]{-10, -1, 2, 4, 5}),
                Arguments.of(new int[]{-5, -6, -11, 2, 7}, new int[]{-11, -6, -5, 2, 7}),
                Arguments.of(new int[]{5, -10, -12, 1, 0, 9}, new int[]{-12, -10, 0, 1, 5, 9}),
                Arguments.of(new int[]{1, 2, -1000, 0, -20000}, new int[]{-20000, -1000, 0, 1, 2})
        );
    }

    /* String test case */
    public static Stream<Arguments> stringStreamProvider(){
        return Stream.of(
                Arguments.of(new String[]{"Ann", "David", "John", "Joan", "Zoe"},
                        new String[]{"Ann", "David", "Joan", "John", "Zoe"}),
                Arguments.of(new String[]{"Georgette Hanson", "Harold Morton",
                        "Violet Holt", "Mirabelle Lynch", "Fred Harding"},
                        new String[]{"Fred Harding", "Georgette Hanson", "Harold Morton",
                                "Mirabelle Lynch", "Violet Holt"})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Arguments = {0}, {1}")
    @MethodSource("integerStreamProvider")
    void priorityQueueRunIntegerTest(int[] random, int[] correct){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int[] result = new int[random.length];
        int index = 0;
        for(int element: random){
            test.add(element);
        }

        while(!test.isEmpty()){
            result[index] = test.poll();
            //System.out.println(correct[index]);
            index++;
        }
        assertArrayEquals(correct, result);
    }


    @ParameterizedTest(name="#{index} - Test with Argument = {0}, {1}")
    @MethodSource("stringStreamProvider")
    void priorityQueueRunStringTest(String[] random, String[] correct){
        PriorityQueue<String> test = new PriorityQueue<String>();
        int index = 0;
        String[] result = new String[random.length];
        for(String element: random){
            test.add(element);
        }
        while(!test.isEmpty()){
            result[index] = test.poll();
            index++;
        }
        assertArrayEquals(result, correct);

    }
    @ParameterizedTest
    @NullSource
    void whenExceptionTrow_addNull(String input){
        PriorityQueue<String> exception_test = new PriorityQueue<String>();
        //exception_test.add(input);
        Exception e = assertThrows(RuntimeException.class, () -> {
            try {
                exception_test.add(input);  // null element is not allowed
            } catch(Exception n){
                throw new NullPointerException("add Null element");
            }
        });
        String errorMsg = e.getMessage();
        assertTrue(errorMsg.contains("Null"));
    }

    @Test
    void whenExceptionTrow_offerNull(){
        PriorityQueue<String> exception_test = new PriorityQueue<String>();
        Exception e = assertThrows(RuntimeException.class,() -> {
            try {
                exception_test.offer(null);
            } catch (Exception n){
                throw new NullPointerException("offer Null");
            }
        });
        String errorMsg = e.getMessage();
        assertTrue(errorMsg.contains("Null"));
    }

    @Test
    void whenExceptionTrow_Illegal(){
        Exception e = assertThrows(RuntimeException.class, () -> {
            try {
                PriorityQueue<String> test = new PriorityQueue<>(0);
            } catch (Exception i){
                throw new IllegalArgumentException("Capacity is not allowed to be less than 1");
            }
        });
        String errMsg = e.getMessage();
        assertTrue(errMsg.contains("Capacity"));
    }

}
