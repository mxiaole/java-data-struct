package com.cody.datastruct.array;

/**
 * 动态数组
 */
public class DynamicArray<E> {
    private int size;
    private E[] data;

    public DynamicArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public DynamicArray() {
        this(10);
    }

    public DynamicArray(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 动态数组的扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    // 向数组中指定位置添加元素
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // 更新数组元素
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error");
        }

        data[index] = e;
    }

    // 从数组中删除元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }
        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        data[size] = null;
        size--;

        // 动态数组缩小容量, 为了防止复杂度的震荡，当容量小于四分之一的时候才进行缩容
        if (size == data.length / 4) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("invalid index");
        }
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 交换a、b两个元素
    public void swap(int i, int j) {

        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("the size is: %d, the capacity is: %d\n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i] + " ");
        }
        sb.append(']');

        return sb.toString();
    }

    public static void main(String[] args) {
        DynamicArray<Integer> array = new DynamicArray<>();
        for (int i = 0; i < 10; i++) {
            array.add(0, i);
        }
        System.out.println(array);
        array.add(0, 20);
        System.out.println(array);
        array.remove(0);
        System.out.println(array);
    }
}
