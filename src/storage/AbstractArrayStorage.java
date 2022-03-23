package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;

/**
 * This class implements abstract logic to store array elements successively
 * at the beginning of the array. Remaining cells are filled with nulls
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    @Override
    public void save(Resume resume) {
        if (resume == null) {
            return;
        }
        String uuid = resume.getUuid();
        int index = indexOf(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Array storage is overflowed when trying to add resume with uuid '" + uuid + "'", uuid);
        }
        put(resume, index);
        size++;
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteFillingEmptyCells(index);
        storage[size - 1] = null;
        size--;
    }

    /**
     * Returns an array which contains only Resumes in the storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * Puts resume in the storage at {@code index} shifting element(s) if needed
     */
    protected abstract void put(Resume resume, int index);

    /**
     * Removes resume from the storage at {@code index} not leaving empty cells
     * between elements
     */
    protected abstract void deleteFillingEmptyCells(int index);

    /**
     * Returns the index of an element with {@code uuid} or any negative value if the element is not found
     */
    protected abstract int indexOf(String uuid);
}
