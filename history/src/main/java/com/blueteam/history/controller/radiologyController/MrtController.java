package com.blueteam.history.controller.radiologyController;

import com.blueteam.history.dto.radiologyDto.MrtDto;
import com.blueteam.history.entity.history.exam.radiology.Mrt;
import com.blueteam.history.service.radiologyService.MrtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mrt")
@RequiredArgsConstructor
public class MrtController {

    private final  MrtService mrtService;

    @GetMapping
    public List<MrtDto> mrtDtoList() {
        return mrtService.findAll()
                .stream()
                .map(MrtDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addNewMrt(@RequestBody MrtDto mrtDto) {
        Mrt mrt = mrtDto.convertToEntity();
        mrtService.add(mrt);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody MrtDto mrtDto) {
        mrtService.update(mrtDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        mrtService.delete(id);
    }

}
