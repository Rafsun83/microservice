package com.example.self_management.controller;

import com.example.self_management.model.domain.Wallet;
import com.example.self_management.model.dto.wallet.CreateWalletRequest;
import com.example.self_management.model.dto.wallet.UpdateWalletRequest;
import com.example.self_management.payload.ApiResponse;
import com.example.self_management.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallets")
@Tag(name = "Get wallet", description = "All wallet get here")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @Operation(summary = "Get All wallet data")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Wallet>>>  getAllWallet() {
        List<Wallet> wallet = walletService.getAllWallet();
        ApiResponse<List<Wallet>> response = new ApiResponse<>(true, "Wallet fetched successfully!", wallet, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get wallet by Id", description = "Get wallet by Id through the API")
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<Wallet>> getWalletById(@PathVariable Long id) {
        Wallet wallet = walletService.getWalletById(id);
        ApiResponse<Wallet> response = new ApiResponse<>(true, "Wallet fetched successfully!", wallet, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create wallet", description = "Create your wallet")
    @PostMapping
    public ResponseEntity<ApiResponse<Wallet>> addWallet(@RequestBody CreateWalletRequest  createWalletRequest) {
        Wallet wallet = walletService.addWallet(createWalletRequest);
        ApiResponse<Wallet> response = new ApiResponse<>(true, "Wallet Created Successfully!", wallet, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update wallet", description = "Update wallet using this API")
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<Object>> updateWallet(@PathVariable Long id, @RequestBody UpdateWalletRequest updateWalletRequest) {
        walletService.updateWallet(id, updateWalletRequest);
        Map<String, Object> data = Map.of("id", id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Wallet Updated Successfully!", data, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Operation(summary="Delete wallet", description = "Delete wallet through the API")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Object>> deleteWallet (@PathVariable Long id) {
        walletService.deleteWallet(id);
        Map<String, Object> data = Map.of("id", id);
        ApiResponse<Object> response = new ApiResponse<>(true, "Wallet Deleted Successfully!", data, HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
