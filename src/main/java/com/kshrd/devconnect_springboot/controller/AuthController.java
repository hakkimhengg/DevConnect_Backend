package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.dto.request.AppUserRequest;
import com.kshrd.devconnect_springboot.model.dto.request.AuthRequest;
import com.kshrd.devconnect_springboot.model.dto.request.ResetRequest;
import com.kshrd.devconnect_springboot.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auths")
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody AuthRequest request) throws Exception {
        return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Login successfully")
                .payload(authService.login(request))
                .build());
    }

    @SneakyThrows
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody AppUserRequest request){
        return response(ApiResponse.builder()
                .success(true)
                .message("Register successfully")
                .status(HttpStatus.CREATED)
                .payload(authService.register(request))
                .build());
    }

    @PostMapping("/verify")
    @Operation(summary = "Verify email with OTP")
    public ResponseEntity<ApiResponse> verify(@Email @RequestParam String email, @RequestParam @Positive(message = "Otp code cannot be negative or zero") String otpCode) {
        authService.verify(email, otpCode);
        return response(ApiResponse.builder()
                .success(true)
                .message("Verified successfully!!!")
                .status(HttpStatus.OK)
                .build());
    }

    @SneakyThrows
    @PostMapping("/resend")
    @Operation(summary = "Resent verification OTP")
    public ResponseEntity<ApiResponse> resend(@Email @RequestParam String email) {
        authService.resend(email);
        return response(ApiResponse.builder()
                .success(true)
                .message("OTP has successfully resent")
                .status(HttpStatus.OK)
                .build());
    }

    @PostMapping("/forgot")
    @Operation(summary = "Send otp code to email")
    public ResponseEntity<ApiResponse> verifyEmail(@Email @RequestParam String email) {
        authService.forgotPassword(email);
        return response(ApiResponse.builder()
                .success(true)
                .message("success")
                .status(HttpStatus.OK)
                .build());
    }

    @PostMapping("/forgot/verify")
    @Operation(summary = "Verify otp in forgot password")
    public ResponseEntity<ApiResponse> verifyForgot(@Email @RequestParam String email, @RequestParam String otp) {
        authService.verifyForgot(email, otp);
        return response(ApiResponse.builder()
                .success(true)
                .message("success")
                .status(HttpStatus.OK)
                .build());
    }

    @PostMapping("/forgot/reset")
    @Operation(summary = "Reset password otp in forgot password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetRequest request) {
        return response(ApiResponse.builder()
                .success(true)
                .message("success")
                .status(HttpStatus.OK)
                .payload(authService.resetPassword(request.getEmail(), request.getOtp(), request.getPassword()))
                .build());
    }
}
