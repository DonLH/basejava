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
        if (indexOf(uuid) != -1) {
            System.out.println("Resume with uuid '" + uuid + "' already exists\n");
            return;
        }
        if (size == STORAGE_LIMIT) {
            System.out.println("Array storage is overflowed when trying to add resume with uuid '" + uuid + "'\n");
            return;
        }
        storage[size++] = resume;
        Arrays.sort(storage, 0, size);
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume with uuid '" + uuid + "' is not yet in the storage\n");
        }
        Arrays.sort(storage, 0, size);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
