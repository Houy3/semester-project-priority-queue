import java.util.*;

public class PriorityQueue implements Queue<Integer> {

    private final MaxBinaryHeap heap;


    /**
     * Creates a {@code PriorityQueue} with the specified initial capacity
     * @param capacity - initial capacity
     */
    public PriorityQueue(int capacity) {
        heap = new MaxBinaryHeap(capacity);
    }

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param el the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     */
    @Override
    public boolean add(Integer el) {
        if (heap.size == heap.capacity) {
            throw new IllegalStateException();
        }
        if (el == null) {
            throw new NullPointerException();
        }
        heap.insert(el);
        return true;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param el the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     */
    @Override
    public boolean offer(Integer el) {
        try {
            add(el);
        } catch (IllegalStateException ex) {
            return false;
        }
        return true;
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll() poll()} only in that it throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Integer remove() {
        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.takeTop();
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public Integer poll() {
        try {
            return remove();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Integer element() {
        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.seeTop();
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    @Override
    public Integer peek() {
        try {
            return element();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }



    private class MaxBinaryHeap {

        private int[] arr;
        private final int capacity;
        private int size;

        // Нет проверок на выход за границы массива, потому что они в реализации очереди

        //конструктор для кучи
        public MaxBinaryHeap(int capacity) {
            arr = new int[capacity];
            this.capacity = capacity;
        }

        //добавляет элемент
        public void insert(Integer p) {
            arr[size] = p;
            shiftUp(size++);
        }

        // Подглядывает элемент с верхушки
        public Integer seeTop() {
            return arr[0];
        }

        // Вычленяет элемент с верхушки
        public Integer takeTop() {
            int result = seeTop();
            arr[0] = arr[--size];
            shiftDown(0);
            return result;
        }

        // Возвращает кол-во элементов в куче
        public int size() {
            return size;
        }




        // Возвращают индексы соответствующих соседей по дереву
        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return ((2 * i) + 1); }
        private int rightChild(int i) { return ((2 * i) + 2); }

        // Меняет объекты кучи местами
        private void swap(int i, int j) {
            int swap = arr[i];
            arr[i] = arr[j];
            arr[j] = swap;
        }

        // Сдвигает объект наверх
        private void shiftUp(int i) {
            while (i > 0 && arr[i] > arr[parent(i)]) {
                swap(parent(i), i);
                i = parent(i);
            }
        }

        // Сдвигает объект вниз
        private void shiftDown(int i) {
            int maxIndex = i;

            int[] miniArr = {leftChild(i), rightChild(i)};
            for (int j : miniArr) {
                if (j < size && arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            if (i != maxIndex) {
                swap(i, maxIndex);
                shiftDown(maxIndex);
            }
        }

    }







    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }



}


