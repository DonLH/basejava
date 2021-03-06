package storage;

import model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void put(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteFillingEmptyCells(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
