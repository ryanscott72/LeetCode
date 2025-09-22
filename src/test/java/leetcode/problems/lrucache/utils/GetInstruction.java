package leetcode.problems.lrucache.utils;

import java.util.List;

public class GetInstruction extends Instruction<List<Integer>> {
  private final int key;
  private final int expectedValue;

  public GetInstruction(final int key, final int expectedValue) {
    this.key = key;
    this.expectedValue = expectedValue;
  }

  public List<Integer> getArgument() {
    return List.of(this.key, this.expectedValue);
  }
}
