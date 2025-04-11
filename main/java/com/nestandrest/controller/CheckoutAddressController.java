package com.nestandrest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Simple Address model class
class Address {
    private String name;
    private String address;
    private String phone;

    public Address(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}

@WebServlet("/checkout-address")
public class CheckoutAddressController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Mock addresses (could be fetched from DB later)
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Lucian Obrien", "1147 Rohan Drive Suite 819 - Burlington, VT / 82021", "904-966-2836"));
        addresses.add(new Address("Deja Brady", "18605 Thompson Circle Apt. 086 - Idaho Falls, WV / 50337", "399-757-9909"));
        addresses.add(new Address("Jayvion Simon", "19034 Verna Unions Apt. 164 - Honolulu, RI / 87535", "365-374-4961"));
        addresses.add(new Address("Harrison Stein", "110 Lamar Station Apt. 730 - Hagerstown, OK / 49808", "692-767-2903"));

        request.setAttribute("addresses", addresses);
        request.getRequestDispatcher("/WEB-INF/pages/checkout/checkout-address.jsp").forward(request, response);
    }
}

