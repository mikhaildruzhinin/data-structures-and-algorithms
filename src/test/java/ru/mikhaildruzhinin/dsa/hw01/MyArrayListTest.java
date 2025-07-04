package ru.mikhaildruzhinin.dsa.hw01;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyArrayListTest {

  @Test
  @Order(1)
  void initMemorySize_IllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new MyArrayList<Integer>(0, 2));
  }

  @Test
  @Order(2)
  void memoryIncreaseRate_IllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> new MyArrayList<Integer>(2, -1));
  }

  @Test
  @Order(3)
  void add_back() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertEquals("[1, 2, 3, 4]", list.toString());
  }

  @Test
  @Order(4)
  void add_back_realloc() {
    MyArrayList<Integer> list = new MyArrayList<>(1, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    assertEquals("[1, 2, 3, 4]", list.toString());
  }

  @Test
  @Order(5)
  void add_middle() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(1, 0);
    assertEquals("[1, 0, 2, 3]", list.toString());
  }

  @Test
  @Order(6)
  void add_first() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(0, 0);
    assertEquals("[0, 1, 2, 3]", list.toString());
  }

  @Test
  @Order(7)
  void add_last() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2, 0);
    assertEquals("[1, 2, 0, 3]", list.toString());
  }

  @Test
  @Order(7)
  void add_middle_realloc() {
    MyArrayList<Integer> list = new MyArrayList<>(1, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(1, 0);
    assertEquals("[1, 0, 2, 3]", list.toString());
  }

  @Test
  @Order(8)
  void add_first_realloc() {
    MyArrayList<Integer> list = new MyArrayList<>(1, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(0, 0);
    assertEquals("[0, 1, 2, 3]", list.toString());
  }

  @Test
  @Order(9)
  void add_end_realloc() {
    MyArrayList<Integer> list = new MyArrayList<>(1, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(2, 0);
    assertEquals("[1, 2, 0, 3]", list.toString());
  }

  @Test
  @Order(10)
  void add_IndexOutOfBoundsException() {
    MyArrayList<Integer> list = new MyArrayList<>(1, 2);
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
  }

  @Test
  @Order(11)
  void get() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(2, list.get(1));
  }

  @Test
  @Order(12)
  void get_IndexOutOfBoundsException() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
  }

  @Test
  @Order(13)
  void remove_back() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(3, list.remove());
    assertEquals("[1, 2]", list.toString());
  }

  @Test
  @Order(14)
  void remove_back_NoSuchElementException() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    assertThrows(NoSuchElementException.class, list::remove);
  }

  @Test
  @Order(15)
  void remove_middle() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(2, list.remove(1));
    assertEquals("[1, 3]", list.toString());
  }

  @Test
  @Order(16)
  void remove_first() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(1, list.remove(0));
    assertEquals("[2, 3]", list.toString());
  }

  @Test
  @Order(17)
  void remove_last() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    assertEquals(3, list.remove(2));
    assertEquals("[1, 2]", list.toString());
  }

  @Test
  @Order(17)
  void remove_IndexOutOfBoundsException() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
  }

  @Test
  @Order(18)
  void replace() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(2);
    list.add(3);
    list.replace(1, 0);
    assertEquals("[1, 0, 3]", list.toString());
  }

  @Test
  @Order(19)
  void replace_IndexOutOfBoundsException() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class, () -> list.replace(-1, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> list.replace(1, 0));
  }

  @Test
  @Order(20)
  void size_empty() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    assertEquals(0, list.size());
  }

  @Test
  @Order(21)
  void size_notEmpty() {
    MyArrayList<Integer> list = new MyArrayList<>(16, 2);
    list.add(1);
    list.add(1);
    list.add(1);
    list.add(1);
    assertEquals(4, list.size());
  }

  @Test
  @Order(22)
  void arrayOfStrings() {
    MyArrayList<String> list = new MyArrayList<>(16, 2);
    list.add("Hello");
    list.add("Bonjour");
    list.add("Hola");
    list.remove(2);
    list.replace(1, "World");
    assertEquals("[Hello, World]", list.toString());
  }
}
