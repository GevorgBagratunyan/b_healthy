package com.blueteam.history.controller;

import com.blueteam.history.dto.HistoryDto;
import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.entity.history.History;
import com.blueteam.history.service.HistoryService;
import com.blueteam.history.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/history")

public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public List<HistoryDto> listHistores() {

        return historyService.findAll()
                .stream()
                .map(HistoryDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public void addNewHistory(@RequestBody HistoryDto historyDto) {
        History history = historyDto.convertToEntity();
        historyService.add(history);
    }

    @PutMapping("/{id}")
    public void updateHistory(@PathParam("id") long id, HistoryDto historyDto) {

        historyDto.setId(id);

        historyService.update(historyDto);
    }
}
