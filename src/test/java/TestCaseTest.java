import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {

    @Test
    void main() {
        TestCaseInterface testCase = new TestCase();

        injection(testCase);

        TestCaseInterface testCaseAlpha = new TestCaseAlpha();

        injection(testCaseAlpha);

//
//        System.out.println(testCase.getName());
//        System.out.println(testCaseAlpha.getName());
    }

    void injection(TestCaseInterface testCase){
        testCase.setName("steven");

        System.out.println(testCase.getName());
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void getYear() {
    }

    @Test
    void setYear() {
    }
}