package com.safepolicy.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalServiceImpl implements PaypalService{

    @Autowired
    private APIContext apiContext;
    
  
    @Override
    public Payment createPayment(Double total, String currency, String method,
                                 String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount theAmount = new Amount();
        theAmount.setCurrency(currency);
        total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
        theAmount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(theAmount);

        List<Transaction> theTransactions = new ArrayList<>();
        theTransactions.add(transaction);

        Payer thePayer = new Payer();
        thePayer.setPaymentMethod(method.toString());

        Payment thePayment = new Payment();
        thePayment.setIntent(intent);
        thePayment.setTransactions(theTransactions);
        thePayment.setPayer(thePayer);

        RedirectUrls theRedirectUrls = new RedirectUrls();
        theRedirectUrls.setCancelUrl("http://localhost:8080/cancel");
        theRedirectUrls.setReturnUrl("http://localhost:8080/success");
        thePayment.setRedirectUrls(theRedirectUrls);
        apiContext.setMaskRequestId(true);
        return thePayment.create(apiContext);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {

        Payment thePayment = new Payment();
        thePayment.setId(paymentId);

        PaymentExecution thPaymentExecution = new PaymentExecution();
        thPaymentExecution.setPayerId(payerId);

        return thePayment.execute(apiContext, thPaymentExecution);
    }
}