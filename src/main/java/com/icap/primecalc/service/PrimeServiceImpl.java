package com.icap.primecalc.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.sqrt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

/**
 * Created by ssinghbi02 on 24/05/2016.
 */
@Service
public class PrimeServiceImpl implements PrimeService {

    private static List<Integer> process(int limit) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
            CompletableFuture<List<Integer>> primes = CompletableFuture.supplyAsync(() ->
                    rangeClosed(2, limit)
                            .filter(PrimeServiceImpl::isPrime)
                            .boxed()
                            .collect(toList()), forkJoinPool);
            return primes.get();
        } catch (InterruptedException e) {
            //Handle exception, log error
            System.out.println("error in processing");
        } catch (ExecutionException e) {
            //Handle exception, log error
            System.out.println("error in processing");
        }
        return null;
    }

    public static boolean isPrime(long n) {
        return n > 1 && rangeClosed(2, (int) sqrt(n)).noneMatch(divisor -> n % divisor == 0);
    }

    private static boolean isPrimeSimple(int value) {
        for (int i = 2; i * i <= value; ++i) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> getPrimeNumbers(int limit) {
        ExecutorService es = Executors.newCachedThreadPool();

        if (limit <= 1) {
            throw new IllegalArgumentException("limit must be greater than 1");
        }

        return process(limit);

    }

}