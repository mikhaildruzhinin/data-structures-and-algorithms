package ru.mikhaildruzhinin.dsa.hw01;

import java.util.NoSuchElementException;

public class MyArrayList<E> {

  private Object[] memory;

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

    int initSize = calculateRaisedToThePower(this.memoryIncreaseBase, this.memoryIncreasePower);
    System.out.println("initSize=" + initSize);
    this.memory = new Object[initSize];
  }

  private static int calculateRaisedToThePower(int base, int power) {
    int result = 1;
    if (power != 0) {
      for (int i = 1; i <= power; i++) {
        result = result * base;
      }
    }
    return result;
  }

  public E get(int index) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
    }
    return (E) memory[index];
  }

  public void add(E element) {
    System.out.println("current_array=" + this);
    System.out.println("inserting element=" + element);
    if (memory.length <= (lastIndex + 1)) {
      memory = increaseMemory();
    }
    memory[lastIndex + 1] = element;
    lastIndex++;
  }

  private Object[] increaseMemory() {
    memoryIncreasePower++;

    int newMemorySize = calculateRaisedToThePower(memoryIncreaseBase, memoryIncreasePower);
    System.out.println("newSize=" + newMemorySize);

    Object[] newMemory = new Object[newMemorySize];
    for (int i = 0; i <= lastIndex; i++) {
      System.out.println("realloc index=" + i + " element=" + memory[i]);
      newMemory[i] = memory[i];
    }
    return newMemory;
  }

  public void add(int index, E element) throws IndexOutOfBoundsException {
    if (index > lastIndex || index < 0) {
      throw new IndexOutOfBoundsException("Array has a minimum index of 0 and a maximum index of " + lastIndex + ".");
    }
    System.out.println("current_array=" + this);
    if (memory.length <= (lastIndex + 1)) {
      increaseMemory();
    }
    for (int i = lastIndex + 1; i >= index; i--) {
      E newElement = (i > 0) ? (E) memory[i - 1] : null;
      System.out.println("shift index=" + i + " old=" + memory[i] + " new=" + newElement);
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
