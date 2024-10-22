package store;

import datatype.Euro;

public class Employee {

    public int id;
    public String name;
    public String surname;
    public Euro balance;

    public Employee(int id, String nome, String cognome, Euro saldo) {
        this.id = id;
        this.name = nome;
        this.surname = cognome;
        this.balance = saldo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Euro getBalance() {
        return balance;
    }

    public String printBalance() {
        return balance.toString();
    }

    public String toString() {
        return (id + " " + name + " " + surname + " " + balance);
    }

    public void printEmployee() {
        System.out.print(id);
        System.out.print(" ");
        System.out.print(name);
        System.out.print(" ");
        System.out.print(surname);
        System.out.print(" ");
        System.out.println(balance);
    }
}
