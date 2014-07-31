package ru.ifmo.ctddev.ivanov.task9;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Application connects to a server and checks or creates a {@link ru.ifmo.ctddev.ivanov.task9.Person}
 * and it's {@link ru.ifmo.ctddev.ivanov.task9.Account} accordingly to specified arguments.
 * Can use both local an remote versions of persons.
 */
public class Client {
    /**
     * Runs the program with specified arguments.
     *
     * @param args first name, last name, passport number, account id and amount of money which
     *             will be set to the account if it exists.
     */
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Wrong number of arguments!");
            return;
        }
        Bank bank;
        try {
            bank = (Bank) Naming.lookup("rmi://localhost/bank");
            Person person;
            if (!bank.personExists(args[2])) {
                bank.createPerson(args[0], args[1], args[2]);
                person = bank.getRemotePerson(args[2]);
                System.out.println("Person created.");
            } else {
                person = bank.getRemotePerson(args[2]);
                System.out.println("Checking first name and last name...");
                System.out.println("First name: " + (args[0].equals(person.getFirstName()) ? "OK" : "Fail"));
                System.out.println("Last name : " + (args[1].equals(person.getLastName()) ? "OK" : "Fail"));
            }
            Account account = person.getAccount(args[3]);
            if (!person.accountExists(args[3])) {
                person.createAccount(args[3]);
                System.out.println("Account created.");
            } else {
                int old = account.getAmount();
                account.changeAmount(Integer.parseInt(args[4]));
                System.out.println("Account amount updated from " + old + " to " + account.getAmount());
            }
        } catch (RemoteException e1) {
            System.err.println("Error while remote operations!");
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            System.err.println("Bad URL!");
            e1.printStackTrace();
        } catch (NotBoundException e1) {
            System.err.println("Bank not found!");
            e1.printStackTrace();
        } catch (NumberFormatException e1) {
            System.err.println("Account change value should be a number!");
        }
    }
}
