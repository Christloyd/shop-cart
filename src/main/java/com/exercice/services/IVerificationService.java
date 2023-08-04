package com.exercice.services;

import java.util.List;
import java.util.Optional;

import com.exercice.http.Cart;

public interface IVerificationService {
	
	public Optional<Double> Verification(List<Cart> carts) throws Exception;

}
