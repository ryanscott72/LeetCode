package leetcode.problems.lrucache.utils;

public class NewInstruction extends Instruction<Integer> {
  private final int capacity;

  public NewInstruction(final int capacity) {
    this.capacity = capacity;
  }

  public Integer getArgument() {
    return this.capacity;
  }
}
