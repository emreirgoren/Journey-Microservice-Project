package com.patika.journey_service.util;


import com.patika.journey_service.model.Journey;
import com.patika.journey_service.repository.JourneyJpaRepository;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.Random;

@Configuration
public class GenerateTicketCode {

    private JourneyJpaRepository journeyJpaRepository;

    public GenerateTicketCode(JourneyJpaRepository journeyJpaRepository) {
        this.journeyJpaRepository = journeyJpaRepository;
    }

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    Random random = new Random();

    public String createUniqueTicketCode(){
        String ticketCode;

        do{
            String randomStr = generateRandomString();
            String randomNumber = generateRandomNumber();
            ticketCode = randomStr + "-" +randomNumber;
        }while (isUniqueTicketCode(ticketCode));
        return ticketCode;
    }

    private boolean isUniqueTicketCode(String ticketCode) {
        Optional<Journey> existingJourney = journeyJpaRepository.findByTicketCode(ticketCode);
        return existingJourney.isPresent();
    }


    private String generateRandomString(){
        ALPHABET.length();
        char firstLetter = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        char secondLetter = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        return String.valueOf(firstLetter)+secondLetter;
    }

    private String generateRandomNumber(){
        StringBuilder randomNumber = new StringBuilder();
        for (int i=0; i<3; i++){
            int randomBasamak = random.nextInt(10);
            randomNumber.append(randomBasamak);
        }
        return randomNumber.toString();


    }





}
