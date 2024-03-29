package ru.ifmo.ctddev.ivanov.task9;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Wraps a {@link ru.ifmo.ctddev.ivanov.task9.LocalAccount} to make it changeable from other JVMs with RMI.
 * Thread-safe.
 */
public class RemoteAccount extends UnicastRemoteObject implements Account {
    private LocalAccount account;

    /**
     * Creates an remote account, wrapping a {@link ru.ifmo.ctddev.ivanov.task9.LocalAccount}.
     *
     * @param account
     */
    public RemoteAccount(LocalAccount account) throws RemoteException {
        super();
        this.account = account;
    }

    /**
     * Return account identifier.
     *
     * @return account id.
     */
    public String getId() {
        return account.getId();
    }

    /**
     * Returns account amount of money.
     *
     * @return amount of money.
     */
    public int getAmount() {
        return account.getAmount();
    }

    /**
     * Changes account amount of money.
     *
     * @param amount new amount.
     */
    public synchronized void changeAmount(int amount) {
        account.changeAmount(amount);
    }
}
