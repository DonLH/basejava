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
    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume with uuid '" + resume.getUuid() + "' is not yet in the storage\n");
        }
        storage[index] = resume;
    }

    /**
     * Returns the index of an element with {@code uuid} or any negative value if the element is not found
     */
    protected abstract int indexOf(String uuid);
}
