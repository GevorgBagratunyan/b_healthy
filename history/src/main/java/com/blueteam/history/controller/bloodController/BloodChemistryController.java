package com.blueteam.history.controller.bloodController;

import com.blueteam.history.dto.bloodDto.BloodChemistryDto;
import com.blueteam.history.entity.history.exam.blood.BloodChemistry;
import com.blueteam.history.service.bloodService.BloodChemistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bloodChemistry")
public class BloodChemistryController {

    @Autowired
    private BloodChemistryService bloodChemistryService;

    @GetMapping
    public List<BloodChemistryDto> bloodChemistryList() {

        return bloodChemistryService.findAll()
                .stream()
                .map(BloodChemistryDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewBloodChemisty(@RequestBody BloodChemistryDto bloodChemistryDto) {
        BloodChemistry chemistry = bloodChemistryDto.convertToEntity();
        bloodChemistryService.add(chemistry);
    }
    @PutMapping("/{id}")
    public void update(@RequestBody BloodChemistryDto bloodChemistryDto) {
        bloodChemistryService.update(bloodChemistryDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        bloodChemistryService.delete(id);
    }

}
