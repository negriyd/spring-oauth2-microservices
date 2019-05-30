package securityapp.model;

public class AccountNotFoundException extends RuntimeException {

    private final String id;

    public AccountNotFoundException(String id) {
        super("securityapp-not-found-" + id);
        this.id = id;
    }

}
