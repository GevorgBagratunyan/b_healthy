package com.blueteam.history.controller.bloodController;

import com.blueteam.history.dto.bloodDto.BloodGeneralDto;
import com.blueteam.history.entity.history.exam.blood.BloodGeneral;
import com.blueteam.history.service.bloodService.BloodGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bloodGeneral")
public class BloodGeneralController {

    @Autowired
    private BloodGeneralService generalService;

    @GetMapping
    public List<BloodGeneralDto> bloodGeneralDtoList() {

        return generalService.findAll()
                .stream()
                .map(BloodGeneralDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewBloodGeneral(@RequestBody BloodGeneralDto generalDto) {
        BloodGeneral bloodGeneral = generalDto.convertToEntity();
        generalService.add(bloodGeneral);

    }


    @PutMapping("/{id}")
    public void update(@RequestBody BloodGeneralDto generalDto) {
        generalService.update(generalDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        generalService.delete(id);
    }
}
