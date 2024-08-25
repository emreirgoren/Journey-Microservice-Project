package com.patika.basket_service.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String NOT_ENOUGH_TICKET = "Not enough ticket";
    public static final String MAX_INDIVIDUAL_TICKET = "Individual user can purchase a maximum of 5 tickets";
    public static final String MAX_INDIVIDUAL_TICKET_FOR_MAN = "Individual user can purchase tickets for up to 2 male passengers in a single order";
    public static final String MAX_CORPORATE_TICKET = "Corporate users can purchase a maximum of 40 tickets for the same flight";

    public static final String TICKET_NOT_FOUND = "Ticket not found";

    public static final String BASKET_NOT_FOUND = "Basket not found";
}
