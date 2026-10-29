package com.shashi.bookmovie.Repository.Book;

import com.shashi.bookmovie.Entity.Book.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {

    Optional<Payment> findByStripePaymentIntentId(String paymentIntentId);

}
