package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    protected static final int STORAGE_LIMIT = 10_000;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Assert.assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID_2);
        Assert.assertEquals(UUID_2, resume.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void get_notExists() {
        storage.get("dummy");
    }

    @Test
    public void save() throws Exception {
        Resume resume = new Resume(UUID_4);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void save_alreadyExists() {
        Resume resume = new Resume(UUID_2);
        storage.save(resume);
    }

    @Test
    public void update() throws Exception {
        Resume oldResume = storage.get(UUID_2);
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assert.assertNotSame(oldResume, storage.get(UUID_2));
        Assert.assertEquals(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void update_notExists() throws Exception {
        Resume resume = new Resume(UUID_4);
        storage.update(resume);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_2);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete_notExists() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = new Resume[]{
                new Resume(UUID_1),
                new Resume(UUID_2),
                new Resume(UUID_3)
        };
        Assert.assertArrayEquals(resumes, storage.getAll());
    }

    @Test(expected = StorageException.class)
    public void testStorageOverflow() {
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                Resume resume = new Resume("uuid" + i);
                storage.save(resume);
            }
        } catch (Exception e) {
            Assert.fail("Storage overflow ahead of time");
        }
        Resume resume = new Resume("uuid" + STORAGE_LIMIT + 1);
        storage.save(resume);
    }
}
