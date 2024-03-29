package ru.ifmo.ctddev.ivanov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Account is a common interface to a data structure, storing an information about person's account.
 * Account can be passed with RMI, so it extends {@link java.rmi.Remote} and each its method
 * throws {@link java.rmi.RemoteException}. If you work with an account, stored on the same JVM,
 * you should use {@link ru.ifmo.ctddev.ivanov.task9.LocalAccount}.
 */
public interface Account extends Remote, Serializable {
    /**
     * Returns account identifier.
     *
     * @return account id.
     * @throws RemoteException
     */
    String getId() throws RemoteException;

    /**
     * Returns account current amount of money.
     *
     * @return account amount of money.
     * @throws RemoteException
     */
    int getAmount() throws RemoteException;

    /**
     * Changes account amount of money.
     *
     * @param amount amount change value.
     * @throws RemoteException
     */
    void changeAmount(int amount) throws RemoteException;
}
