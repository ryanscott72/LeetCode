package leetcode.problems.lrucache;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Stream;
import leetcode.problems.lrucache.utils.GetInstruction;
import leetcode.problems.lrucache.utils.Instruction;
import leetcode.problems.lrucache.utils.NewInstruction;
import leetcode.problems.lrucache.utils.PutInstruction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LRUCacheTest {
  private static final Logger LOGGER = LogManager.getLogger();

  private static Stream<Arguments> testCasesProvider() {
    return Stream.of(
        Arguments.of(
            new NewInstruction(2),
            List.of(
                new PutInstruction(new AbstractMap.SimpleEntry<>(1, 1)),
                new PutInstruction(new AbstractMap.SimpleEntry<>(2, 2)),
                new GetInstruction(1, 1),
                new PutInstruction(new AbstractMap.SimpleEntry<>(3, 3)),
                new GetInstruction(2, -1),
                new PutInstruction(new AbstractMap.SimpleEntry<>(4, 4)),
                new GetInstruction(1, -1),
                new GetInstruction(3, 3),
                new GetInstruction(4, 4))),
        Arguments.of(
            new NewInstruction(1),
            List.of(
                new GetInstruction(6, -1),
                new GetInstruction(8, -1),
                new PutInstruction(new AbstractMap.SimpleEntry<>(12, 1)),
                new GetInstruction(2, -1),
                new PutInstruction(new AbstractMap.SimpleEntry<>(15, 11)),
                new PutInstruction(new AbstractMap.SimpleEntry<>(5, 2)),
                new PutInstruction(new AbstractMap.SimpleEntry<>(1, 15)),
                new PutInstruction(new AbstractMap.SimpleEntry<>(4, 2)),
                new GetInstruction(4, 2),
                new PutInstruction(new AbstractMap.SimpleEntry<>(15, 15)))));
  }

  @ParameterizedTest
  @MethodSource("testCasesProvider")
  void testCases(final NewInstruction newInstruction, final List<Instruction<?>> instructions) {
    printTestDivider();
    LRUCache lruCache = new LRUCache(newInstruction.getArgument());
    for (Instruction<?> instruction : instructions) {
      printOperationDivider();
      if (instruction instanceof GetInstruction getInstruction) {
        assertEquals(
            getInstruction.getArgument().get(1), lruCache.get(getInstruction.getArgument().get(0)));
      }
      if (instruction instanceof PutInstruction putInstruction) {
        lruCache.put(
            putInstruction.getArgument().getKey(), putInstruction.getArgument().getValue());
      }
    }
  }

  private void printTestDivider() {
    LOGGER.debug("################################################");
  }

  private void printOperationDivider() {
    LOGGER.debug("------------------------------------------------");
  }
}
