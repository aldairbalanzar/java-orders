package com.aldair.orders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderid;

    private double ordamount;
    private double advanceamount;
    private double custcode;
    private String orderdescription;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "orderpayments",
            joinColumns = @JoinColumn(name = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "paymentid"))
    private Set<Payment> payments = new HashSet<>();

    public Order() {
        // default jpa constructor
    }

    public Order(double ordamount, double advanceamount, double custcode, String orderdescription, Customer customer) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.custcode = custcode;
        this.orderdescription = orderdescription;
        this.customer = customer;
    }

    public Order(double v, double v1, Customer c19, String sod) {
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public double getCustcode() {
        return custcode;
    }

    public void setCustcode(double custcode) {
        this.custcode = custcode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
