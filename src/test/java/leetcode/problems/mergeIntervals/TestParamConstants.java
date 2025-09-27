package leetcode.problems.mergeIntervals;

public class TestParamConstants {
  // Case 1
  public static final int[][] INPUT_ONE = {
    {1, 3},
    {2, 6},
    {8, 10},
    {15, 18}
  };
  public static final int[][] EXPECTED_ONE = {
    {1, 6},
    {8, 10},
    {15, 18}
  };

  // Case 2
  public static final int[][] INPUT_TWO = {
    {1, 4},
    {4, 5}
  };
  public static final int[][] EXPECTED_TWO = {{1, 5}};

  // Case 3
  public static final int[][] INPUT_THREE = {
    {4, 7},
    {1, 4}
  };
  public static final int[][] EXPECTED_THREE = {{1, 7}};

  // Case 4 (no merges)
  public static final int[][] INPUT_FOUR = {{1, 2}};
  public static final int[][] EXPECTED_FOUR = {{1, 2}};

  // Case 5 (sequential)
  public static final int[][] INPUT_FIVE = {
    {2, 3},
    {4, 5},
    {6, 7},
    {8, 9},
    {1, 10}
  };
  public static final int[][] EXPECTED_FIVE = {{1, 10}};
}
