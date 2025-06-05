package ru.mikhaildruzhinin.dsa.hw01;

import java.util.NoSuchElementException;

public class MyArrayList<E> {

  private Object[] memory;

  private int lastIndex = -1;

  private int memorySize;

  private final int memoryIncreaseRate;

  public MyArrayList(int initMemorySize, int memoryIncreaseRate) throws IllegalArgumentException {
    if (initMemorySize < 1) {
      throw new IllegalArgumentException("Value of initMemorySize cannot be less than 1.");
    }
    if (memoryIncreaseRate < 1) {
      throw new IllegalArgumentException("Value of memoryIncreaseRate cannot be less than 1.");
    }

    memorySize = initMemorySize;
    this.memoryIncreaseRate = memoryIncreaseRate;
    this.memory = new Object[memorySize];
  }

  public E get(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
    }
    return (E) memory[index];
  }

  public void add(E element) {
    if (memory.length <= (lastIndex + 1)) {
      memory = increaseMemory();
    }
    memory[lastIndex + 1] = element;
    lastIndex++;
  }

  private Object[] increaseMemory() {
    memorySize = memorySize * memoryIncreaseRate;
    Object[] newMemory = new Object[memorySize];
    for (int i = 0; i <= lastIndex; i++) {
      newMemory[i] = memory[i];
    }
    return newMemory;
  }

  public void add(int index, E element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
    }
    if (memory.length <= (lastIndex + 1)) {
      memory = increaseMemory();
    }
    for (int i = lastIndex + 1; i >= index; i--) {
      E newElement = (i > 0) ? (E) memory[i - 1] : null;
      memory[i] = newElement;
    }
    memory[index] = element;
    lastIndex++;
  }

  public E remove() throws NoSuchElementException {
    if (lastIndex < 0) {
      String errorMessage = "Element does not exist.";
      throw new NoSuchElementException(errorMessage);
    }
    E deletedElement = (E) memory[lastIndex];
    lastIndex--;
    return deletedElement;
  }

  public E remove(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
    }
    E deletedElement = (E) memory[index];
    for (int i = index + 1; i <= lastIndex + 1 ; i++) {
      memory[i - 1] = memory[i];
    }
    lastIndex--;
    return deletedElement;
  }

  public void replace(int index, E element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
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
