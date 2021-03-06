package com.gelvides.for_alpha.controllers;

import com.gelvides.for_alpha.feign.CurrencyRestClient;
import com.gelvides.for_alpha.feign.MediaRestClient;
import com.gelvides.for_alpha.services.MediaService;
import com.gelvides.for_alpha.services.MoneyService;
import com.gelvides.for_alpha.interfaces.GettingYesterdayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController implements GettingYesterdayDate{
    @Autowired
    MediaService mediaService;
    @Autowired
    MediaRestClient mediaRestClient;
    @Autowired
    MoneyService moneyService;
    @Autowired
    CurrencyRestClient restClient;

    @GetMapping()
    public String goPage(Model model){
        var priceToday = moneyService.comparisonVolume(restClient.getCource());
        var priceYesterday = moneyService.comparisonVolume(restClient.getCource(getYesterdayDate()));
        model.addAttribute("priceToday", priceToday);
        model.addAttribute("priceYesterday", priceYesterday);
        if(priceToday.priceCurrency() < priceYesterday.priceCurrency()){
            model.addAttribute("media", mediaService.createMedia(mediaRestClient.getPositiveMedia()));
        } else
            model.addAttribute("media", mediaService.createMedia(mediaRestClient.getNegativeMedia()));
        return "index";
    }
}
