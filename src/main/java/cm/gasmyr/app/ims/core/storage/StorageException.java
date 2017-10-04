package cm.gasmyr.app.ims.core.storage;

public class StorageException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6588155783222396368L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
