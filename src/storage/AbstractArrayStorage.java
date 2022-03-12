package storage;

import model.Resume;

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
        clearStorage();
        size = 0;
    }

    @Override
    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
            return null;
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
            System.out.println("Resume with uuid '" + uuid + "' already exists\n");
            return;
        }
        if (size == STORAGE_LIMIT) {
            System.out.println("Array storage is overflowed when trying to add resume with uuid '" + uuid + "'\n");
            return;
        }
        save(resume, index);
        size++;
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid '" + resume.getUuid() + "' is not yet in the storage\n");
        }
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
        }
        delete(index);
        size--;
    }

    /**
     * Clears stored elements
     */
    protected abstract void clearStorage();

    /**
     * Puts resume in the storage at {@code index} without checks
     */
    protected abstract void save(Resume resume, int index);

    /**
     * Removes resume from the storage at {@code index}
     */
    protected abstract void delete(int index);

    /**
     * Returns the index of an element with {@code uuid} or any negative value if the element is not found
     */
    protected abstract int indexOf(String uuid);
}
