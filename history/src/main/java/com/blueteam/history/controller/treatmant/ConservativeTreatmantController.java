package com.blueteam.history.controller.treatmant;

import com.blueteam.history.dto.treatment.ConservativeTreatmentDto;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.service.treatmant.ConservativeTreatmantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consTreatmant")
@RequiredArgsConstructor
public class ConservativeTreatmantController {

    private final ConservativeTreatmantService conservativeTreatmantService;

    @GetMapping
    public List<ConservativeTreatmentDto> conservativeTreatmentDtoList() {

        return conservativeTreatmantService.findAll()
                .stream()
                .map(ConservativeTreatmentDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewConservativTreatmant(@RequestBody ConservativeTreatmentDto conservativeTreatmentDto) {
        ConservativeTreatment conservativeTreatment = conservativeTreatmentDto.convertToEntity();
        conservativeTreatmantService.add(conservativeTreatment);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ConservativeTreatmentDto conservativeTreatmentDto) {
        conservativeTreatmantService.update(conservativeTreatmentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        conservativeTreatmantService.delete(id);
    }
}
