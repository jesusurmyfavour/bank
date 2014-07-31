package ru.ifmo.ctddev.ivanov.task9;

/**
 * LocalAccount provides an access to a {@link ru.ifmo.ctddev.ivanov.task9.Account} stored on the same JVM.
 * Non-thread-safe.
 */
public class LocalAccount implements Account {
    private String id;
    private int amount;

    /**
     * Creates an account with a specified id and zero amount of money.
     *
     * @param id account identifier.
     */
    public LocalAccount(String id) {
        this.id = id;
    }

    /**
     * Return account id.
     *
     * @return identifier.
     */
    public String getId() {
        return id;
    }

    /**
     * Return account amount of money.
     *
     * @return amount of money.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Changes an amount of money on a specified value.
     *
     * @param amount new amount.
     */
    public void changeAmount(int amount) {
        this.amount += amount;
    }
}
