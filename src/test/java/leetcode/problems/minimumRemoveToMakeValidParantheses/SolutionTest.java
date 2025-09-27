package leetcode.problems.minimumRemoveToMakeValidParantheses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionTest {

  private final Solution solution = new Solution();

  private static Stream<Arguments> provideTestParams() {
    return Stream.of(
        Arguments.of("lee(t(c)o)de)", List.of("lee(t(c)o)de", "lee(t(co)de)", "lee(t(c)ode)")),
        Arguments.of("a)b(c)d", List.of("ab(c)d")),
        Arguments.of("))((", List.of("")),
        Arguments.of("(a(b(c)d)", List.of("a(b(c)d)")),
        Arguments.of("())()(((", List.of("()()")));
  }

  @ParameterizedTest
  @MethodSource("provideTestParams")
  void minRemoveToMakeValid(final String input, final List<String> acceptableAnswers) {
    assertTrue(acceptableAnswers.contains(solution.minRemoveToMakeValid(input)));
  }
}
