package exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume with uuid '" + uuid + "' is not yet in the storage", uuid);
    }
}
