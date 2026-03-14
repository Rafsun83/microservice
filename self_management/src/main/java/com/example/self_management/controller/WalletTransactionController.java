package com.example.self_management.controller;

import com.example.self_management.model.domain.WalletTransaction;
import com.example.self_management.model.dto.walletTransaction.CreateWalletTransactionRequest;
import com.example.self_management.payload.ApiResponse;
import com.example.self_management.service.WalletTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet/{walletId}/transaction")
public class WalletTransactionController {
    private final WalletTransactionService walletTransactionService;
    public WalletTransactionController(WalletTransactionService walletTransactionService) {
        this.walletTransactionService = walletTransactionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WalletTransaction>>> getAllTransactions(@PathVariable Long walletId) {
        List<WalletTransaction> allTransactionList = walletTransactionService.getAllWalletTransaction(walletId);
        ApiResponse<List<WalletTransaction>> apiResponse = new ApiResponse<>(true, "TransactionList fetched successfully!!", allTransactionList, HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<WalletTransaction>> createWalletTransaction(@PathVariable Long walletId, @RequestBody CreateWalletTransactionRequest createWalletTransactionRequest ) {
        WalletTransaction walletTransaction = walletTransactionService.createWalletTransaction(walletId, createWalletTransactionRequest);
        ApiResponse<WalletTransaction> apiResponse = new ApiResponse<>(true, "Transaction completed successfully!!", walletTransaction, HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
}
