package ru.mikhaildruzhinin.dsa.hw01;

import java.util.NoSuchElementException;

public class MyArrayList {

  private int[] memory;

  private int lastIndex = -1;

  private final int memoryIncreaseSize;

  public MyArrayList(int initSize, int memoryIncreaseSize) {
    this.memory = new int[initSize];
    this.memoryIncreaseSize = memoryIncreaseSize;
  }

  public int get(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    return memory[index];
  }

  public void add(int element) {
    if (memory.length < lastIndex + 1) {
      memory = new int[lastIndex + memoryIncreaseSize];
    }
    memory[lastIndex + 1] = element;
    lastIndex++;
  }

  public void add(int index, int element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException(); // TODO: add error message
    }
    if (memory.length < lastIndex + 1) {
      memory = new int[lastIndex + memoryIncreaseSize];
    }
    for (int i = lastIndex; i > index; i--) {
      memory[i] = memory[i - 1];
    }
    memory[index] = element;
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
    for (int i : memory) {
      sb.append(memory[i]);
      if (i < memory.length - 1) {
        sb.append(", ");
      } else {
        sb.append("]");
      }
    }
    return sb.toString();
  }
}
