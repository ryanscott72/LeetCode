package leetcode.problems.mergeIntervals;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solution {
  private static final Logger LOGGER = LogManager.getLogger();

  public int[][] merge(int[][] intervals) {
    final List<List<Integer>> mergedIntervals = new ArrayList<>();
    boolean overlapFound = false;

    // TODO Can I improve the time complexity from O(n^2)?
    for (int[] interval : intervals) {
      if (mergedIntervals.isEmpty()) {
        mergedIntervals.add(List.of(interval[0], interval[1]));
      } else {
        for (int i = 0; i < mergedIntervals.size(); i++) {
          List<Integer> mergedInterval = mergedIntervals.get(i);
          if (newIntervalOverlapsWithExistingInterval(interval, mergedInterval)) {
            overlapFound = true;
            mergedIntervals.set(
                i,
                List.of(
                    Math.min(interval[0], mergedInterval.get(0)),
                    Math.max(interval[1], mergedInterval.get(1))));
            break;
          }
        }
        if (!overlapFound) {
          mergedIntervals.add(List.of(interval[0], interval[1]));
        }
        overlapFound = false;
      }
    }

    // TODO try to avoid this by using primitive data type
    return convertBoxedToPrimitive(mergedIntervals);
  }

  /**
   * To determine if two intervals, ([A1,A2]) and ([B1,B2]), overlap, a concise mathematical
   * condition can be used: The intervals overlap if and only if the maximum of their left
   * boundaries is less than or equal to the minimum of their right boundaries. Expressed
   * mathematically: max(A1, B1) <= min(A2, B2) This checks for the presence of an intersection
   * between both intervals. If one exists, they overlap. If one does not exist, they do not overlap
   */
  private boolean newIntervalOverlapsWithExistingInterval(
      final int[] newInterval, final List<Integer> existingInterval) {
    boolean overlaps =
        Math.max(newInterval[0], existingInterval.get(0))
            <= Math.min(newInterval[1], existingInterval.get(1));
    if (overlaps) {
      LOGGER.info("Overlap detected");
    }
    return overlaps;
  }

  private int[][] convertBoxedToPrimitive(final List<List<Integer>> boxed) {
    int[][] primitiveArray = new int[boxed.size()][];
    for (int i = 0; i < boxed.size(); i++) {
      List<Integer> innerList = boxed.get(i);
      int cols = innerList.size();
      primitiveArray[i] = new int[cols]; // Initialize inner array

      for (int j = 0; j < cols; j++) {
        primitiveArray[i][j] = innerList.get(j);
      }
    }
    return primitiveArray;
  }
}
