package com.devsu.api.web.controllers;

import com.devsu.api.application.dtos.MovementDTO;
import com.devsu.api.application.dtos.reports.AccountStatementDTO;
import com.devsu.api.application.dtos.reports.AccountStatementParams;
import com.devsu.api.application.validations.Create;
import com.devsu.api.domain.services.MovementService;
import com.devsu.api.domain.services.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/reportes")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<AccountStatementDTO> generateAccountReport(@Validated @ModelAttribute AccountStatementParams params) {
        log.info("GET - /reportes/{}", params);

        return new ResponseEntity<>(this.reportService.generateAccountReport(params), HttpStatus.OK);
    }

}
