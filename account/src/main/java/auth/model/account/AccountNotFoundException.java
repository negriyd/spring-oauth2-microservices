package auth.model.account;

public class AccountNotFoundException extends RuntimeException {

    private final String id;

    public AccountNotFoundException(String id) {
        super("auth-not-found-" + id);
        this.id = id;
    }

}
