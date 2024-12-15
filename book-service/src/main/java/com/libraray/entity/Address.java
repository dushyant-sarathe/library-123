package com.libraray.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@NotNull
	@NotEmpty
	@NotBlank
	private String complexNo;

	@NotNull
	@NotEmpty
	@NotBlank
	private String complexName;

	@NotNull
	@NotEmpty
	@NotBlank
    private String streetNo;


	@NotNull
	@NotEmpty
	@NotBlank
	private String streetName;

	@NotNull
	@NotEmpty
	@NotBlank
	private String suburb;

	@NotNull
	@NotEmpty
	@NotBlank
	private String  province;

//	    @NotNull
//	    @NotEmpty
//	    @NotBlank
//    private String state;

	    @NotNull
	    @NotEmpty
	    @NotBlank
    private String country;

	    @NotNull
	    @NotEmpty
	    @NotBlank
    private String postalCode;
	    
	    @NotNull
	    @NotEmpty
	    @NotBlank
    private String poBox;
	    
	    @NotNull
	    @NotEmpty
	    @NotBlank
	private String poSuburb;

	   
}
