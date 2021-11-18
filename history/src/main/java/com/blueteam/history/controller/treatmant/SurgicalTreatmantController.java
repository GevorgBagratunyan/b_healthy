package com.blueteam.history.controller.treatmant;

import com.blueteam.history.dto.treatment.ConservativeTreatmentDto;
import com.blueteam.history.dto.treatment.SurggicalTreatmentDto;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import com.blueteam.history.service.treatmant.SurgicalTreatmantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surgicalTreatmant")
@RequiredArgsConstructor
public class SurgicalTreatmantController {

    private final SurgicalTreatmantService surgicalTreatmantService;

    @GetMapping
    public List<SurggicalTreatmentDto> surggicalTreatmentDtos() {

        return surgicalTreatmantService.findAll()
                .stream()
                .map(SurggicalTreatmentDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewSurgicalTreatmant(@RequestBody SurggicalTreatmentDto surggicalTreatmentDto) {
        SurgicalTreatment surgicalTreatment = surggicalTreatmentDto.convertToEntity();
        surgicalTreatmantService.add(surgicalTreatment);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody SurggicalTreatmentDto surggicalTreatmentDto) {
        surgicalTreatmantService.update(surggicalTreatmentDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        surgicalTreatmantService.delete(id);
    }
}
