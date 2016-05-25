package com.icap.primecalc.service;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimeServiceImplTest {

    private PrimeService primeService;

    @Before
    public void init() {
        primeService = new PrimeServiceImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgument() {
        primeService.getPrimeNumbers(1);
    }

    @Test
    public void testOneValue() {
        List<Integer> primes = primeService.getPrimeNumbers(2);

        assertEquals(1, primes.size());
        assertEquals(2, primes.get(0).intValue());
    }

    @Test
    public void testMultipleValues() {
        List<Integer> primes = primeService.getPrimeNumbers(10);

        assertEquals(4, primes.size());
        assertEquals(2, primes.get(0).intValue());
        assertEquals(3, primes.get(1).intValue());
        assertEquals(5, primes.get(2).intValue());
        assertEquals(7, primes.get(3).intValue());
    }

}