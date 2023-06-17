package com.HW.project;

import java.util.ArrayList;

// данный коммент ради появления возможности пул реквеста

public class OwnArrayDrist {
    private static Object[] array = new Object[10];
    private static int nextSlot = 0;
    private static final int INITIAL_SIZE = 10;

    public OwnArrayDrist() {
        array = new Object[INITIAL_SIZE];
    }

    public OwnArrayDrist(int CUSTOM_SIZE) {
        array = new Object[CUSTOM_SIZE];
    }

    public static void main(String[] args) {
        OwnArrayDrist someShit = new OwnArrayDrist();
        someShit.add(0, 2);
        someShit.add(1, 2);
        someShit.add(9, 2);
        someShit.remove(100);


        someShit.showAll();
    }

    private void extendArrayTool() {
        Object[] extended = new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            extended[i] = array[i];
        }
        array = extended;
    }

    public void add(Object element) {
        if (nextSlot < array.length) {
            array[nextSlot] = element;
        }
        extendArrayTool();
        nextSlot++;
    }

    public void add(int indexForPut, Object element) {
        if (indexForPut >= array.length - 1) { // оставил тут array.length , потому что если оставить nextSlot , как было указано в твоем комментарии , то при ситуации когда nexSlot например равен 2 нам придется делать еще одну проверку на выход за границу массива (  то есть реально ли мы вышли за границу массива ) , хотя мы можем это проверить сразу .
            extendArrayTool();
            int initialLength = array.length / 2;
            for (int i = 1; i < (initialLength - 1 - indexForPut); i++) {
                array[((initialLength - 1) - i)] = array[((initialLength - 2) - i)];
            }
        } else if (array[indexForPut] != null) {
            for (int i = 0; i < ((array.length - 1) - indexForPut); i++) {
                array[(array.length - 1) - i] = array[(array.length - 2) - i];
            }
        }
        array[indexForPut] = element;
        nextSlot++;
    }

    public Object get(int indexToGet) {
        return array[indexToGet];
    }

    public void remove(int indexToDelete) throws IndexOutOfBoundsException {
        if (indexToDelete < array.length) {
            for (int i = indexToDelete; i < array.length - indexToDelete - 1; i++) {
                array[i] = array[i + 1];
            }
        } else if (indexToDelete == array.length) {
            array[indexToDelete] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    void showAll() { // для себя сделал чтобы смотреть внутрянку быстро
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
