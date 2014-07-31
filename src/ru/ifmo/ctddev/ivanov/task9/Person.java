package ru.ifmo.ctddev.ivanov.task9;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Person is a common interface to a data structure, storing an information about a person.
 * Person can be passed with RMI, so it extends {@link java.rmi.Remote} and each it's method
 * throws {@link java.rmi.RemoteException}. If you work with a person stored on the same JVM
 * you should use {@link ru.ifmo.ctddev.ivanov.task9.LocalPerson}.
 */
public interface Person extends Remote, Serializable {
    /**
     * Return persons first name.
     *
     * @return first name.
     * @throws RemoteException
     */
    String getFirstName() throws RemoteException;

    /**
     * Returns persons last name.
     *
     * @return last name.
     * @throws RemoteException
     */
    String getLastName() throws RemoteException;

    /**
     * Returns persons passport number.
     *
     * @return passport number.
     * @throws RemoteException
     */
    String getPassportNumber() throws RemoteException;

    /**
     * Returns an account with a specified identifier. If person is local it will return a local account and
     * remote account otherwise. If you work with a remote person you should check a presence of an account
     * with {@code accountExists} method.
     *
     * @param id account id.
     * @return requested account.
     * @throws RemoteException
     */
    Account getAccount(String id) throws RemoteException;

    /**
     * Creates an account with specified identifier. If it already exists, it will be recreated with
     * a zero amount of money.
     *
     * @param id account id.
     * @throws RemoteException
     */
    void createAccount(String id) throws RemoteException;

    /**
     * Checks whatever a person has an account with specified identifier.
     *
     * @param id account id.
     * @return true if account is present, false otherwise.
     * @throws RemoteException
     */
    boolean accountExists(String id) throws RemoteException;
}
