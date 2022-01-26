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

    void save(Resume r) {
        if (r == null) {
            return;
        }
        if (get(r.uuid) != null) {
            return;
        }
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];
            if (resume.uuid == uuid) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            Resume resume = storage[i];
            if (resume.uuid == uuid) {
                shiftLeftRemoving(i);
            }
        }
    }

    /**
     * Removes element at <code>removeIndex</code> shifting right-hand elements one position left
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
        return this.size;
    }
}
