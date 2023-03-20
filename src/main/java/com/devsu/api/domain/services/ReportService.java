package com.devsu.api.domain.services;

import com.devsu.api.application.dtos.reports.AccountStatementDTO;
import com.devsu.api.application.dtos.reports.AccountStatementParams;

public interface ReportService {
    AccountStatementDTO generateAccountReport(AccountStatementParams params);

}
