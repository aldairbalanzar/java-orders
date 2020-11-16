package com.aldair.orders;

import com.github.javafaker.Faker;

import com.aldair.orders.models.Agent;
import com.aldair.orders.models.Customer;
import com.aldair.orders.models.Order;
import com.aldair.orders.models.Payment;
import com.aldair.orders.repositories.AgentRepository;
import com.aldair.orders.repositories.CustomerRepository;
import com.aldair.orders.repositories.OrderRepository;
import com.aldair.orders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner {

/**
 * Connects the customer table to this SeedData method
 */

    @Autowired
    private CustomerRepository customerrepo;

    /**
     * Connects the agents table to this SeedData method
     */

    @Autowired
    private AgentRepository agentrepo;

    /**
     * Connects the orders table to this SeedData method
     */

    @Autowired
    private OrderRepository orderrepo;

    /**
     * Connects the payment table to this SeedData method
     */

    @Autowired
    private PaymentRepository paymentrepo;

    /**
     * A Random generator is needed to randomly generate faker data.
     */

    private Random random = new Random();

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */

    @Transactional
    @Override
    public void run(String[] args) throws

            Exception {
        Payment pay1 = new Payment("Cash");
        Agent a10 = new Agent("McDen",
                "London",
                0.15,
                "078-22255588",
                "");

        //Begins the faker data

        Faker dataFaker = new Faker(new Locale("en-US"));
        Set<String> customerNames = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            customerNames.add(dataFaker.name()
                    .fullName());
        }

        for (String theName : customerNames) {
            String custcity = dataFaker.address()
                    .city();
            String tempWorkingarea = dataFaker.address()
                    .cityName();
            String tempCustcountry = dataFaker.address()
                    .country();
            String tempGrade = dataFaker.country()
                    .countryCode2();
            double tempOpeningamt = dataFaker.number()
                    .randomDouble(2,
                            0,
                            10000);
            double tempReceiveamt = dataFaker.number()
                    .randomDouble(2,
                            0,
                            10000);
            double tempPaymentamt = dataFaker.number()
                    .randomDouble(2,
                            0,
                            10000);
            double tempOutstandingamt = dataFaker.number()
                    .randomDouble(2,
                            0,
                            10000);
            String tempPhone = dataFaker.phoneNumber()
                    .phoneNumber();

            Customer fakeCustomer = new Customer(theName,
                    custcity,
                    tempWorkingarea,
                    tempCustcountry,
                    tempGrade,
                    tempOpeningamt,
                    tempReceiveamt,
                    tempPaymentamt,
                    tempOutstandingamt,
                    tempPhone,
                    a10);

            int randomNumber = random.nextInt(10); // random number 0 through 9
            for (int i = 0; i < randomNumber; i++) {
                double tempGetOrdamount = dataFaker.number()
                        .randomDouble(2,
                                0,
                                10000);
                double tempGetAdvanceamount = dataFaker.number()
                        .randomDouble(2,
                                0,
                                10000);
                String tempGetOrderdescription = dataFaker.lorem()
                        .characters();

                Order newOrder = new Order(tempGetOrdamount,
                        tempGetAdvanceamount,
                        fakeCustomer,
                        tempGetOrderdescription);

                newOrder.getPayments().add(pay1);
                fakeCustomer.getOrderList()
                        .add(newOrder);
            }

            // this actually saves the faker data.
            customerrepo.save(fakeCustomer);
        }
    }
}
