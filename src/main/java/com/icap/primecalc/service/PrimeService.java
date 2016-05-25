package com.icap.primecalc.service;

import java.util.List;

/**
 * Interface for prime number calculation.
 */
public interface PrimeService {

    List<Integer> getPrimeNumbers(int limit);

}
