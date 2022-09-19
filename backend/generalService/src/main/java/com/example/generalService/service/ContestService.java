package com.example.generalService.service;

import com.example.generalService.dto.ContestDto;
import com.example.generalService.entity.Contest;

import java.util.List;
import java.util.Optional;

public interface ContestService {

    ContestDto save (ContestDto contestDto);

    Optional<ContestDto> findById (String id);

    Iterable<Contest> findAll();

    List<ContestDto> findByType(String type);

    Boolean startContest(ContestDto contestDto);

    void deleteById(String id);
}

