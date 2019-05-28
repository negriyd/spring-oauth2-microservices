package account.model;

public class AccountNotFoundException extends RuntimeException {

    private final String id;

    public AccountNotFoundException(String id) {
        super("account-not-found-" + id);
        this.id = id;
    }

}
