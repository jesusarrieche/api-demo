package com.devsu.api.domain.repositories;

import com.devsu.api.application.dtos.reports.AccountStatementParams;
import com.devsu.api.application.dtos.reports.MovementsSummary;
import com.devsu.api.domain.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT m FROM Movement m WHERE " +
            "DATE(m.date) = DATE(:date) " +
            "AND m.account.id = :accountId")
    List<Movement> findMovementsByToday(Long accountId, LocalDate date);

    @Query("SELECT IFNULL(SUM(m.amount), 0) FROM Movement m " +
            "WHERE (m.date = :today) " +
            "AND (m.amount < 0) " +
            "AND (m.account.accountNumber = :accountNumber)")
    BigDecimal getTotalWithdrawalAmountByDayAndAccountNumber(LocalDate today, String accountNumber);

    @Query("SELECT new com.devsu.api.application.dtos.reports.MovementsSummary(IFNULL(SUM(m.amount),0), IFNULL(COUNT(m),0)) " +
            "FROM Movement m " +
            "WHERE m.date BETWEEN :#{#params.fechaInicial} AND :#{#params.fechaFinal} " +
            "AND ((m.amount < 0 AND :aux = 1) OR (m.amount >= 0 AND :aux = 0)) " +
            "AND m.account.accountNumber = :#{#params.numeroCuenta} ")
    MovementsSummary getMovementsSummary(
            @Param("params") AccountStatementParams params,
            @Param("aux") Integer aux
    );


//    @Query("SELECT new com.example.dto.MovementsSummary(SUM(m.amount), COUNT(m)) " +
//            "FROM Movement m " +
//            "WHERE m.date BETWEEN :startDate AND :endDate " +
//            "AND m.amount " + (isNegative ? "< 0" : "> 0") +
//            "AND m.account.accountNumber = :accountNumber")
//    MovementsSummary getMovementsSummary(LocalDate startDate, LocalDate endDate, boolean isNegative, String accountNumber)

}
