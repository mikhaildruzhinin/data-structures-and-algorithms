package ru.mikhaildruzhinin.dsa.hw01;

import java.util.NoSuchElementException;

public class MyArrayListV2<E> {

  private Object[] memory;

  private Object[] oldMemory;

  private int lastIndex = -1;

  private int memorySize;

  private final int memoryIncreaseRate = 2;

  public MyArrayListV2(int initMemorySize) throws IllegalArgumentException {
    if (initMemorySize < 1) {
      throw new IllegalArgumentException("Value of initMemorySize cannot be less than 1.");
    }

    memorySize = initMemorySize;
    this.memory = new Object[memorySize];
  }

  public E get(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (E) ((memory[index] != null) ? memory[index] : oldMemory[index]);
    // TODO: move element to new memory if null
  }

  public void add(E element) {
    if (lastIndex >= memorySize - 1) {
      increaseMemory();
    }
    if (oldMemory != null) {
      int oldElementIndex = lastIndex + 1 - memorySize / 2;
      memory[oldElementIndex] = oldMemory[oldElementIndex];
    }
    memory[lastIndex + 1] = element;
    lastIndex++;
  }

  public E remove() throws NoSuchElementException {
    if (lastIndex == -1) {
      throw new NoSuchElementException();
    }
    E removedElement = (E) memory[lastIndex];
    lastIndex--;
    return removedElement;
  }

  private void increaseMemory() {
    oldMemory = memory;
    memorySize = memorySize * memoryIncreaseRate;
    memory = new Object[memorySize];
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
