package auth.controller;

import auth.model.account.Account;
import auth.model.account.AccountNotFoundException;
import auth.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/account")
    ResponseEntity<List<Account>> getCollection() {
        List<Account> collect = this.accountRepository.findAll().stream()
                .collect(Collectors.<Account>toList());
        return ResponseEntity.ok(collect);
    }

    @PostMapping(value = "/account")
    ResponseEntity<Account> save(@RequestBody Account a) {
        Account account = this.accountRepository.save(new Account(a.getUsername(), a.getPassword(), a.isActive()));
        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/account/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(account);
    }

    @GetMapping(value = "/account/{id}")
    ResponseEntity<Account> get(@PathVariable Long id) {
        return this.accountRepository.findById(id)
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new AccountNotFoundException(String.valueOf(id)));
    }

    @GetMapping(value = "/account/username/{username}")
    ResponseEntity<Account> getByName(@PathVariable String username) {
        return this.accountRepository.findByUsername(username)
                .map(c -> ResponseEntity.ok(c))
                .orElseThrow(() -> new AccountNotFoundException(username));
    }
}
