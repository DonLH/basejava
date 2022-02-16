package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (resume == null) {
            return;
        }
        if (indexOf(resume.uuid) != -1) {
            System.out.println("Resume with uuid '" + resume.uuid + "' already exists\n");
            return;
        }
        if (size == storage.length) {
            System.out.println("Array storage is overflowed when trying to add resume with uuid '" + resume.uuid + "'\n");
            return;
        }
        storage[size++] = resume;
    }

    public void update(Resume resume) {
        int index = indexOf(resume.uuid);
        if (index != -1) {
            storage[index] = resume;
        }
        System.out.println("Resume with uuid '" + resume.uuid + "' is not yet in the storage\n");
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
        return null;
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
        System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
    }

    /**
     * Returns array which contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    /**
     * Returns the element with {@code uuid} or -1 if the element is not found
     */
    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return i;
            }
        }
        return -1;
    }
}
