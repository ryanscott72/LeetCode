package minimumRemoveToMakeValidParantheses;

import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO Improve runtime complexity
public class Solution {
  private static final Logger LOGGER = LogManager.getLogger();

  public String minRemoveToMakeValid(final String s) {
    LOGGER.info("Input: {}", s);
    StringBuilder answer = new StringBuilder();
    int count = 0;
    final LinkedList<Integer> invalidIndices = new LinkedList<>(List.of(-1));

    for (int i = 0; i < s.length(); i++) {
      LOGGER.debug("Current answer: {}", answer.toString());
      this.printIndexTracker(invalidIndices);
      char c = s.charAt(i);
      if (c == '(') {
        LOGGER.debug("Found '{}', increasing count from {} to {}", c, count, count + 1);
        count++;
        LOGGER.debug("Adding index as first");
        invalidIndices.addFirst(i);
        this.printIndexTracker(invalidIndices);
      } else if (c == ')') {
        if (count > 0) {
          LOGGER.debug(
              "Found '{}' while count ({}) is greater than 0, completing one pair of parentheses",
              c,
              count);
          invalidIndices.addLast(i);
          this.printIndexTracker(invalidIndices);
          LOGGER.debug("Removing first and last from index tracker");
          invalidIndices.removeFirst();
          invalidIndices.removeLast();
          this.printIndexTracker(invalidIndices);
          count--;
        } else {
          LOGGER.debug("Found '{}' while count ({}) is 0, invalid", c, count);
          invalidIndices.addLast(i);
          this.printIndexTracker(invalidIndices);
        }
      }
      answer.append(c);
    }

    while (!invalidIndices.isEmpty()) {
      int lastIndex = invalidIndices.removeLast();
      if (lastIndex != -1) {
        LOGGER.debug("Removing index {} from {}", lastIndex, answer.toString());
        answer.replace(lastIndex, lastIndex + 1, "_");
      }
    }

    LOGGER.debug("Returning {}", answer.toString().replaceAll("_", ""));
    return answer.toString().replaceAll("_", "");
  }

  private void printIndexTracker(final LinkedList<Integer> indexTracker) {
    StringBuilder log = new StringBuilder();
    log.append("[");
    for (Integer i : indexTracker) {
      log.append(i);
      log.append(",");
    }
    log.deleteCharAt(log.length() - 1);
    log.append("]");
    LOGGER.debug(log.toString());
  }
}
