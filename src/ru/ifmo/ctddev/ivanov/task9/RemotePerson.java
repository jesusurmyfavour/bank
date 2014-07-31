package ru.ifmo.ctddev.ivanov.task9;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Wraps a {@link ru.ifmo.ctddev.ivanov.task9.LocalPerson} to make it changeable from other JVMs with RMI.
 * Thread-safe.
 */
public class RemotePerson extends UnicastRemoteObject implements Person {
    private LocalPerson person;
    private Map<LocalAccount, RemoteAccount> exported = new HashMap<>();

    /**
     * Creates a remote person, wrapping a {@link ru.ifmo.ctddev.ivanov.task9.LocalPerson}.
     *
     * @param person
     */
    public RemotePerson(LocalPerson person) throws RemoteException {
        super();
        this.person = person;
    }

    /**
     * Return persons first name.
     *
     * @return first name.
     */
    public String getFirstName() {
        return person.getFirstName();
    }

    /**
     * Returns persons last name.
     *
     * @return last name.
     */
    public String getLastName() {
        return person.getLastName();
    }

    /**
     * Returns persons passport number.
     *
     * @return passport number.
     */
    public String getPassportNumber() {
        return person.getPassportNumber();
    }

    /**
     * Returns persons account with specified identifier. Account will be passed with RMI.
     * Account presence should be checked first with {@code accountExists} method.
     *
     * @param id account id.
     * @return requested account.
     * @throws RemoteException
     */
    public synchronized Account getAccount(String id) throws RemoteException {
        LocalAccount account = person.getAccount(id);
        RemoteAccount remoteAccount;
        if (exported.containsKey(account)) {
            remoteAccount = exported.get(account);
        } else {
            remoteAccount = new RemoteAccount(account);
            exported.put(account, remoteAccount);
        }
        return remoteAccount;
    }

    /**
     * Creates an account with specified identifier and zero amount of money. If account already exists
     * it will be recreated.
     *
     * @param id account id.
     */
    public synchronized void createAccount(String id) {
        if (!person.accountExists(id)) {
            person.createAccount(id);
        }
    }

    /**
     * Checks whatever a person has an account with specified identifier.
     *
     * @param id account id.
     * @return true if account is present, false otherwise.
     */
    public synchronized boolean accountExists(String id) {
        return person.accountExists(id);
    }
}
