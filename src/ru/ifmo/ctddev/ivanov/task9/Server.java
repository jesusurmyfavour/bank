package ru.ifmo.ctddev.ivanov.task9;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Publishes a remote bank, which will serve clients requests.
 */
public class Server {
    private static Bank bank;

    /**
     * Runs the server.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        bank = new BankImpl();
        try {
            Naming.rebind("rmi://localhost/bank", UnicastRemoteObject.exportObject(bank, 0));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
