package ru.mikhaildruzhinin.dsa.hw01;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyArrayListV2Test {

  @Test
  @Order(1)
  void initMemorySize_IllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new MyArrayListV2<Integer>(0));
  }

  @Test
  @Order(2)
  void add_back() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertEquals("[1, 2, 3, 4]", list.toString());
  }

  @Test
  @Order(3)
  void add_back_realloc() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(1);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertEquals("[1, 2, 3, 4]", list.toString());
  }

  @Test
  @Order(4)
  void get() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(2, list.get(1));
  }

  @Test
  @Order(5)
  void get_IndexOutOfBoundsException() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
  }

  @Test
  @Order(6)
  void remove_back() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(3, list.remove());
    assertEquals("[1, 2]", list.toString());
  }

  @Test
  @Order(7)
  void remove_back_NoSuchElementException() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    assertThrows(NoSuchElementException.class, list::remove);
  }

  @Test
  @Order(8)
  void size_empty() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    assertEquals(0, list.size());
  }

  @Test
  @Order(9)
  void size_notEmpty() {
    MyArrayListV2<Integer> list = new MyArrayListV2<>(16);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);
    assertEquals(4, list.size());
  }
}
