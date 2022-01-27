import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (resume == null) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == resume.uuid) {
                storage[i] = resume;
                return;
            }
        }
        storage[size++] = resume;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                shiftLeftRemoving(i);
            }
        }
    }

    /**
     * Removes element at {@code removeIndex} shifting right-hand elements one position left
     *
     * @param removeIndex index of an element being removed
     */
    private void shiftLeftRemoving(int removeIndex) {
        for (int i = removeIndex; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
