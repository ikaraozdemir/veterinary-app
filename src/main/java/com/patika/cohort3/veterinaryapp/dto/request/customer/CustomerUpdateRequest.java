package com.patika.cohort3.veterinaryapp.dto.request.customer;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
public class CustomerUpdateRequest {
    @NotNull(message = "Customer id cannot be null")
    private Long id;
    @NotBlank(message = "Customer name cannot be blank")
    private String name;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String mpNo;
    @Email(message = "Invalid email format")
    private String mail;
    private String address;
    private String city;
}
