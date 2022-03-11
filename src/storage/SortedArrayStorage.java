package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int indexOf(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume resume) {
        if (resume == null) {
            return;
        }
        String uuid = resume.getUuid();
        int insertionPoint = -indexOf(uuid) - 1;
        if (insertionPoint < 0) {
            System.out.println("Resume with uuid '" + uuid + "' already exists\n");
            return;
        }
        if (size == STORAGE_LIMIT) {
            System.out.println("Array storage is overflowed when trying to add resume with uuid '" + uuid + "'\n");
            return;
        }
        System.arraycopy(storage, insertionPoint,
                storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
        }
        System.arraycopy(storage, index + 1,
                storage, index, size - index - 1);
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
