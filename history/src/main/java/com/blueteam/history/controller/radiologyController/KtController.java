package com.blueteam.history.controller.radiologyController;

import com.blueteam.history.dto.PatientDto;
import com.blueteam.history.dto.radiologyDto.KtDto;
import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.radiology.Kt;
import com.blueteam.history.service.PatientService;
import com.blueteam.history.service.radiologyService.KtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kt")
public class KtController {

    @Autowired
    private KtService ktService;

    @GetMapping
    public List<KtDto> ktDtoList() {
        return ktService.findAll()
                .stream()
                .map(KtDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewKt(@RequestBody KtDto ktDto) {
        Kt kt = ktDto.convertToEntity();
        ktService.add(kt);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody KtDto ktDto) {
        ktService.update(ktDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        ktService.delete(id);
    }

}
