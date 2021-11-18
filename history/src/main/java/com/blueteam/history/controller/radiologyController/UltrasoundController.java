package com.blueteam.history.controller.radiologyController;

import com.blueteam.history.dto.radiologyDto.UltrasoundDto;
import com.blueteam.history.entity.history.exam.radiology.Ultrasound;
import com.blueteam.history.service.radiologyService.UltrasoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ultrasound")
@RequiredArgsConstructor
public class UltrasoundController {

    private final UltrasoundService ultrasoundService;

    @GetMapping
    public List<UltrasoundDto> ultrasoundDtoList() {
        return ultrasoundService.findAll()
                .stream()
                .map(UltrasoundDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewUltrasound(@RequestBody UltrasoundDto ultrasoundDto) {
        Ultrasound ultrasound = ultrasoundDto.convertToEntity();
        ultrasoundService.add(ultrasound);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UltrasoundDto ultrasoundDto) {
        ultrasoundService.update(ultrasoundDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        ultrasoundService.delete(id);
    }

}
