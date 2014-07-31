package ru.ifmo.ctddev.ivanov.task9;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class is used to serve remote requests to {@link ru.ifmo.ctddev.ivanov.task9.Bank}. Stores it's
 * data in a {@link java.util.Map}.
 */
public class BankImpl implements Bank, Serializable {
    private Map<String, LocalPerson> persons = new HashMap<>();
    private Map<LocalPerson, RemotePerson> exported = new HashMap<>();

    /**
     * Tries to find an appropriate record about requested person and return a local
     * version of it to a client.
     *
     * @param passportNumber passport number of requested person
     * @return requested person.
     */
    public synchronized LocalPerson getLocalPerson(String passportNumber) {
        return persons.get(passportNumber);
    }

    /**
     * Tries to find an appropriate record about requested person and return a remote
     * version of it to a client.
     *
     * @param passportNumber passport number of requested person.
     * @return requested person
     * @throws RemoteException
     */
    public synchronized RemotePerson getRemotePerson(String passportNumber) throws RemoteException {
        LocalPerson person = persons.get(passportNumber);
        RemotePerson remotePerson;
        if (exported.containsKey(person)) {
            remotePerson = exported.get(person);
        } else {
            remotePerson = new RemotePerson(person);
            exported.put(person, remotePerson);
        }
        return remotePerson;
    }

    /**
     * Creates a record about a person accordingly to a provided data.
     *
     * @param firstName      first name of person.
     * @param lastName       last name of a person.
     * @param passportNumber passport number of a person.
     */
    public synchronized void createPerson(String firstName, String lastName, String passportNumber) {
        if (!persons.containsKey(passportNumber)) {
            persons.put(passportNumber, new LocalPerson(firstName, lastName, passportNumber));
        }
    }

    /**
     * Check a presence of a person with specified passport number.
     *
     * @param passportNumber passport number of a person.
     * @return true if it is present, false otherwise.
     */
    public synchronized boolean personExists(String passportNumber) {
        return persons.containsKey(passportNumber);
    }
}
