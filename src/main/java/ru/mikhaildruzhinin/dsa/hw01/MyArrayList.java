package ru.mikhaildruzhinin.dsa.hw01;

import java.util.NoSuchElementException;

public class MyArrayList {

  private int[] memory;

  private int lastIndex = -1;

  private final int memoryIncreaseBase;

  private int memoryIncreasePower;

  public MyArrayList(int memoryIncreaseBase, int initMemoryIncreasePower) throws IllegalArgumentException {
    if (memoryIncreaseBase <= 1) {
      throw new IllegalArgumentException("Value of memoryIncreaseBase cannot be less than or equal to 1.");
    }
    if (initMemoryIncreasePower < 0) {
      throw new IllegalArgumentException("Value of initMemoryIncreasePower cannot be negative.");
    }
    this.memoryIncreaseBase = memoryIncreaseBase;
    memoryIncreasePower = initMemoryIncreasePower;
    int initSize = (int) Math.ceil(Math.pow(memoryIncreaseBase, initMemoryIncreasePower));
    System.out.println("initSize=" + initSize);
    this.memory = new int[initSize];
  }

  public int get(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    return memory[index];
  }

  public void add(int element) {
    System.out.println("current_array=" + this);
    System.out.println("inserting element=" + element);
    if (memory.length <= (lastIndex + 1)) {
      memory = increaseMemory();
    }
    memory[lastIndex + 1] = element;
    lastIndex++;
  }

  private int[] increaseMemory() {
    memoryIncreasePower++;
    int newMemorySize = (int) Math.ceil(Math.pow(memoryIncreaseBase, memoryIncreasePower));
    System.out.println("newSize=" + newMemorySize);
    int[] newMemory = new int[newMemorySize];
    for (int i = 0; i <= lastIndex; i++) {
      System.out.println("realloc index=" + i + " element=" + memory[i]);
      newMemory[i] = memory[i];
    }
    return newMemory;
  }

  public void add(int index, int element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    System.out.println("current_array=" + this);
    if (memory.length <= (lastIndex + 1)) {
      increaseMemory();
    }
    for (int i = lastIndex + 1; i >= index; i--) {
      int newElement = (i > 0) ? memory[i - 1] : 0;
      System.out.println("shift index=" + i + " old=" + memory[i] + " new=" + newElement);
      memory[i] = newElement;
    }
    memory[index] = element;
    lastIndex++;
  }

  public int remove() throws NoSuchElementException {
    if (lastIndex < 0) {
      String errorMessage = "Element does not exist.";
      throw new NoSuchElementException(errorMessage);
    }
    int deletedElement = memory[lastIndex];
    lastIndex--;
    return deletedElement;
  }

  public int remove(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    int deletedElement = memory[index];
    for (int i = index + 1; i <= lastIndex + 1 ; i++) {
      memory[i - 1] = memory[i];
    }
    lastIndex--;
    return deletedElement;
  }

  public void replace(int index, int element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    memory[index] = element;
  }

  public int size() {
    return lastIndex + 1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i <= lastIndex; i++) {
      sb.append(memory[i]);
      if (i != lastIndex) {
        sb.append(", ");
      }
    }
    return sb.append("]").toString();
  }
}
