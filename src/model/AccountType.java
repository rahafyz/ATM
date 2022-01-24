package model;

public enum AccountType {
    seporde(5),jari(8),gharzolhasane(10);

    private int interest;

    AccountType(int interest) {
        this.interest = interest;
    }
}

