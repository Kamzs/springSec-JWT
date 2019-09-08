package com.example.spring_sec_jwt;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationAPi {

    private List<Quotation> quotations;

    public QuotationAPi()
    {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("cytat1","autor1"));
        quotations.add(new Quotation("cytat2","autor1"));
        quotations.add(new Quotation("cytat3","autor3"));
    }

    @GetMapping("/cytaty")
    public List<Quotation> getQuotations()
    {return quotations;}


    @PostMapping("/cytaty")
    public boolean addQuotation(@RequestBody Quotation quotation)
    {return quotations.add(quotation);}


    @DeleteMapping("/cytaty")
    public void deleteQuotation(@RequestParam int index)
    {quotations.remove(index);}


}
