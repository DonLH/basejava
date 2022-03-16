package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void put(Resume resume, int index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint,
                storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = resume;
    }

    @Override
    protected void deleteFillingEmptyCells(int index) {
        System.arraycopy(storage, index + 1,
                storage, index, size - index - 1);
    }

    @Override
    protected int indexOf(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
