package com.cg.controller;


import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.service.CustomerService;
import com.cg.service.DepositService;
import com.cg.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DepositService depositService;

    @Autowired
    private TransferService transferService;

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

    @GetMapping("/deposit/{customerId}")
    public ModelAndView showDepositPage(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            modelAndView.setViewName("error/404");
            return modelAndView;
        }

        modelAndView.addObject("customer", customerOptional.get());
        modelAndView.addObject("deposit", new Deposit());


        return modelAndView;
    }

    @GetMapping("/transfer/{senderId}")
    public ModelAndView showTransferPage(@PathVariable Long senderId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        Optional<Customer> senderOptional = customerService.findById(senderId);

        if (!senderOptional.isPresent()) {
            modelAndView.setViewName("error/404");
            return  modelAndView;
        }

        List<Customer> recipients = customerService.findByIdIsNot(senderId);

        modelAndView.addObject("sender", senderOptional.get());
        modelAndView.addObject("recipients", recipients);
        modelAndView.addObject("transfer", new Transfer());

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

    @PostMapping("/deposit/{customerId}")
    public ModelAndView doCreate(@PathVariable Long customerId, @ModelAttribute Deposit deposit) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/deposit");

        Optional<Customer> customerOptional = customerService.findById(customerId);

        Customer customer = customerOptional.get();

        BigDecimal currentBalance = customer.getBalance();

        BigDecimal newBalance;

        BigDecimal transactionAmount = deposit.getTransactionAmount();

        newBalance = currentBalance.add(transactionAmount);

        customer.setBalance(newBalance);

        deposit.setCustomer(customer);
        depositService.save(deposit);

        Customer newCustomer = customerService.save(customer);

        modelAndView.addObject("customer", newCustomer);
        modelAndView.addObject("deposit", new Deposit());

        return modelAndView;
    }

    @PostMapping("/transfer/{senderId}")
    public ModelAndView doTransfer(@PathVariable Long senderId, @ModelAttribute Transfer transfer) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("customer/transfer");

        Optional<Customer> senderOptional = customerService.findById(senderId);
        Customer sender = senderOptional.get();

        BigDecimal currentSenderBalance = sender.getBalance();

        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal fees = new BigDecimal(10L);
        BigDecimal feesAmount = transferAmount.multiply(fees).divide(new BigDecimal(100L));
        BigDecimal transactionAmount = transferAmount.add(feesAmount);

        if (transactionAmount.compareTo(currentSenderBalance) > 0) {
            modelAndView.addObject("errors", "Số dư tài khoản không đủ thực hiện giao dịch");
        } else {
            transfer.setFees(fees.longValueExact());
            transfer.setFeesAmount(feesAmount);
            transfer.setTransactionAmount(transactionAmount);
            transfer.setSender(senderOptional.get());

            transferService.save(transfer);

            currentSenderBalance = currentSenderBalance.subtract(transactionAmount);
            sender.setBalance(currentSenderBalance);

            sender = customerService.save(sender);

            Customer recipient = transfer.getRecipient();
            BigDecimal newRecipientBalance = recipient.getBalance();
            newRecipientBalance = newRecipientBalance.add(transferAmount);
            recipient.setBalance(newRecipientBalance);

            customerService.save(recipient);
        }

        List<Customer> recipients = customerService.findByIdIsNot(senderId);

        modelAndView.addObject("sender", sender);
        modelAndView.addObject("recipients", recipients);
        modelAndView.addObject("transfer", new Transfer());


        return modelAndView;
    }
}
