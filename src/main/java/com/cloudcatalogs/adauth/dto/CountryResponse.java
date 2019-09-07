package com.cloudcatalogs.adauth.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created on 2019-06-05.
 */
@Data
public class CountryResponse implements Comparable{
    private String name;
    private String code;

    public CountryResponse(String countryCode){
        Locale locale = new Locale("", countryCode);

        this.name = locale.getDisplayCountry();
        this.code = locale.getCountry();
    }

    public static List<CountryResponse> allCountries(){

        return Arrays.stream(Locale.getISOCountries())
                .map(CountryResponse::new)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Object o) {
        return this.getName().compareTo(((CountryResponse) o).getName());
    }
}
