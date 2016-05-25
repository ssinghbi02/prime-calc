package com.icap.primecalc.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PrimesResponse {

    @XmlAttribute
    @JsonProperty("Initial")
    private int initial;

    @XmlAttribute
    @JsonProperty("Primes")
    private List<Integer> primes;

    public PrimesResponse() {
    }

    public PrimesResponse(int Initial, List<Integer> Primes) {
        this.initial = Initial;
        this.primes = Primes;
    }

    public int getInitial() {
        return initial;
    }

    public List<Integer> getPrimes() {
        return primes;
    }
}
