package com.icap.primecalc.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ssinghbi02 on 24/05/2016.
 */
@Service
public class PrimeServiceImpl implements PrimeService {

    private static boolean isPrime(int value) {
        for (int i = 2; i * i <= value; ++i) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> getPrimeNumbers(int limit) {
        if (limit <= 1) {
            throw new IllegalArgumentException("limit must be greater than 1");
        }

        return IntStream
                .rangeClosed(2, limit)
                .filter(PrimeServiceImpl::isPrime)
                .boxed()
                .collect(Collectors.toList());
    }

}