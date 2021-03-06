package com.company;

import java.util.Collection;
import java.util.Iterator;

    public class MyLinkedList<T> implements Iterable<T> {


        private class Element {
            public Element nextPointer;
            public Element previousPointer;
            public T value;
        }

        private Element firstElement;
        private Element lastElement;
        private int quantityElement;

        public MyLinkedList() {
            quantityElement = 0;
        }

        public MyLinkedList(Collection<T> collection) {
            quantityElement = 0;
            for (T elements : collection) {
                addLast(elements);
                quantityElement++;
            }
        }

        public int getQuantityElement() {
            return quantityElement;
        }

        public T getFirstElement() {
            return firstElement.value;
        }

        public T getLastElement() {
            return lastElement.value;
        }

        public T get(int index) throws Exception {
            if (index < 0 || index > quantityElement - 1) {
                throw new Exception("Incorrect index");
            }
            Element curr = firstElement;
            while (index != 0) {
                index--;
                curr = curr.nextPointer;
            }
            return curr.value;
        }

        public T removeIndex(int index) throws Exception {
            if (index < 0 || index > quantityElement - 1) {
                throw new Exception("Incorrect index");
            }
            Element curr = firstElement;
            while (index != 0) {
                index--;
                curr = curr.nextPointer;
            }

            if (firstElement == null) {
                return curr.value;
            } else if (firstElement == curr) {
                removeFirst();
                quantityElement--;
                return curr.value;
            } else if (lastElement == curr) {
                lastElement.previousPointer.nextPointer = null;
                quantityElement--;
            } else {
                curr.previousPointer.nextPointer = curr.nextPointer;
                curr.nextPointer.previousPointer = curr.previousPointer;
                quantityElement--;
            }
            return curr.value;
        }

        public void addFirst(T value) {
            Element element = new Element();
            element.value = value;
            if (firstElement == null) {
                firstElement = element;
                lastElement = element;
                quantityElement++;
            } else {
                element.nextPointer = firstElement;
                firstElement.previousPointer = element;
                firstElement = element;
                quantityElement++;
            }
        }

        public void addLast(T value) {
            Element element = new Element();
            element.value = value;
            if (firstElement == null) {
                firstElement = element;
                lastElement = element;
                quantityElement++;
            } else {
                element.previousPointer = lastElement;
                lastElement.nextPointer = element;
                lastElement = element;
                quantityElement++;
            }
        }

        public void removeFirst() {
            if (firstElement != null) {
                firstElement = firstElement.nextPointer;
                quantityElement--;
                if (quantityElement != 0) {
                    firstElement.previousPointer = null;
                }
            }
        }

        public void removeLast() {
            if (firstElement == null) {
                return;
            }

            if (firstElement == lastElement) {
                firstElement = null;
                lastElement = null;
                quantityElement--;
                return;
            }

            lastElement = lastElement.previousPointer;
            lastElement.nextPointer = null;
            quantityElement--;
        }

        public boolean remove(T value) {
            if (firstElement == null) {
                return false;
            }

            if (firstElement.value == value) {
                removeFirst();
                quantityElement--;
                return true;
            }

            Element element = firstElement;

            while (element.nextPointer != null) {
                if (element.nextPointer.value.equals(value)) {
                    if (lastElement == element.nextPointer) {
                        lastElement = element;
                        lastElement.nextPointer = null;
                        quantityElement--;
                    } else {
                        element.nextPointer.previousPointer = null;
                        element.nextPointer = element.nextPointer.nextPointer;
                        element.nextPointer.previousPointer = element;
                        quantityElement--;
                        return true;
                    }
                }
                element = element.nextPointer;
            }

            return false;
        }

        public void removeAll() {
            firstElement = null;
            lastElement = null;
            quantityElement = 0;
        }

        public boolean func1() {
            if (!(firstElement.value instanceof Double) && !(firstElement.value instanceof Integer)) {
                return false;
            }
//        Element minMaxElement = firstElement;
            Element maxElement = firstElement;
            Element element = firstElement;
            while (element != null) {
                if ((Double) element.value > (Double) maxElement.value) {
                    maxElement = element;
                }
                element = element.nextPointer;
            }
            Element newElement = new Element();
            newElement.value = maxElement.value;
            Double buffer = (Double) newElement.value * -1;
            newElement.value = (T) buffer;
            element = maxElement.nextPointer;
            maxElement.nextPointer = newElement;
            newElement.previousPointer = maxElement;
            newElement.nextPointer = element;
            if (element != null) {
                element.previousPointer = newElement;
            } else {
                lastElement = newElement;
            }
            quantityElement++;
            return true;
        }


        private void toSwap(Element element) {
            Element buffer;
            buffer = element.nextPointer.nextPointer;
            element.nextPointer.nextPointer = element.nextPointer.nextPointer.nextPointer;
            buffer.nextPointer = element.nextPointer;
            element.nextPointer = buffer;

        }

        private void toSwapFirstElement(Element element) {
            Element buffer;
            buffer = element.nextPointer;
            element.nextPointer = element.nextPointer.nextPointer;
            buffer.nextPointer = element;
            firstElement = buffer;
        }

        public void sort() {
            Element element = firstElement;
            for (int i = 1; i < quantityElement; i++) {
                if (element.value.hashCode() > element.nextPointer.value.hashCode()) {
                    toSwapFirstElement(element);
                    element = firstElement;
                }
                for (int j = 1; j < quantityElement - 1; j++) {
                    if (element.nextPointer.value.hashCode() > element.nextPointer.nextPointer.value.hashCode()) {
                        toSwap(element);
                    }
                    element = element.nextPointer;
                }
                lastElement = element.nextPointer;
                element = firstElement;
            }
        }

        @Override
        public Iterator<T> iterator() {
            class RangeIterator implements Iterator<T> {
                private Element next;

                private RangeIterator(Element firstElement) {
                    next = firstElement;
                }

                @Override
                public boolean hasNext() {
                    return next != null;
                }

                @Override
                public T next() {
                    T element = next.value;
                    next = next.nextPointer;
                    return element;
                }
            }

            return new RangeIterator(firstElement);
        }
    }

