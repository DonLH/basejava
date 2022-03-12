package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    /**
     * Returns an array which contains only Resumes in the storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected void save(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void delete(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
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
