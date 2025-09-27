package leetcode.problems.mergeIntervals;

import static leetcode.problems.mergeIntervals.TestParamConstants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

  private static Stream<Arguments> testProvider() {
    return Stream.of(
        Arguments.of(INPUT_ONE, EXPECTED_ONE),
        Arguments.of(INPUT_TWO, EXPECTED_TWO),
        Arguments.of(INPUT_THREE, EXPECTED_THREE),
        Arguments.of(INPUT_FOUR, EXPECTED_FOUR),
        Arguments.of(INPUT_FIVE, EXPECTED_FIVE));
  }

  @ParameterizedTest
  @MethodSource("testProvider")
  void merge(final int[][] input, final int[][] expected) {
    Solution solution = new Solution();
    int[][] actual = solution.merge(input);

    if (expected.length != actual.length) {
      fail(
          "Arrays have different lengths. Expected: "
              + expected.length
              + ", Actual: "
              + actual.length);
    }

    for (int i = 0; i < expected.length; i++) {
      assertArrayEquals(expected[i], actual[i]);
    }
  }
}
