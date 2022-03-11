package storage;

import model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int indexOf(String uuid) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
