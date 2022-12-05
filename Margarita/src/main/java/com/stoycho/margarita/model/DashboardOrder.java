package com.stoycho.margarita.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
public class DashboardOrder {
    private LocalDate date;
    private Long quantity;


}
