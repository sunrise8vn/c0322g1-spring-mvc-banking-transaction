package com.cg.controller;


import com.cg.model.Customer;
import com.cg.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/list");


        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");
        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/create");

        customer.setId(0L);
        customer.setBalance(new BigDecimal(0L));
        customerService.save(customer);

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }
}
