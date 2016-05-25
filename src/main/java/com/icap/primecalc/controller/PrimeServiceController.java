package com.icap.primecalc.controller;

import com.icap.primecalc.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PrimeServiceController {

    @Autowired
    private PrimeService primeService;

    @RequestMapping(path = "/primes/{limit}", produces = {"application/json", "application/xml"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<PrimesResponse> primes(@PathVariable("limit") int limit) {
        if (limit <= 1) {
            // primes are positive > 1
            return new ResponseEntity<PrimesResponse>(HttpStatus.BAD_REQUEST);
        }

        List<Integer> primes = primeService.getPrimeNumbers(limit);
        return new ResponseEntity<PrimesResponse>(new PrimesResponse(limit, primes), HttpStatus.OK);
    }

}