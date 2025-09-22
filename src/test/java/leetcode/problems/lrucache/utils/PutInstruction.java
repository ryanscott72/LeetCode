package leetcode.problems.lrucache.utils;

import java.util.Map;

public class PutInstruction extends Instruction<Map.Entry<Integer, Integer>> {
  private final Map.Entry<Integer, Integer> entry;

  public PutInstruction(final Map.Entry<Integer, Integer> entry) {
    this.entry = entry;
  }

  public Map.Entry<Integer, Integer> getArgument() {
    return this.entry;
  }
}
