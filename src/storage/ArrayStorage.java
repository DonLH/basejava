package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (resume == null) {
            return;
        }
        String uuid = resume.getUuid();
        if (indexOf(uuid) != -1) {
            System.out.println("Resume with uuid '" + uuid + "' already exists\n");
            return;
        }
        if (size == STORAGE_LIMIT) {
            System.out.println("Array storage is overflowed when trying to add resume with uuid '" + uuid + "'\n");
            return;
        }
        storage[size++] = resume;
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
        }
    }

    /**
     * Returns array which contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * Returns the element with {@code uuid} or -1 if the element is not found
     */
    protected int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
