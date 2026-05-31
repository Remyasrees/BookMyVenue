package com.bookmyvenue.backend.entity;
import com.bookmyvenue.backend.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "payment_reference",unique = true,length = 100)
    private String paymentReference;

    @Column(name = "amount",nullable = false,precision = 12,scale=2)
    private String amount;

    @Column(name = "payment_method",length = 50)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_status",nullable = false,length = 30)
    private PaymentStatus paymentStatus =PaymentStatus.PENDING;

    @Column(name = "transaction_id",length = 100)
    private String transactionId;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "refund_amount",precision = 12,scale=2)
    private BigDecimal refundAmount=BigDecimal.ZERO;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    protected void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if(this.refundAmount==null)
        {
            this.refundAmount=BigDecimal.ZERO;
        }

        if(this.paymentStatus==null)
        {
            this.paymentStatus=PaymentStatus.PENDING;
        }


    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = LocalDateTime.now();
    }






}
