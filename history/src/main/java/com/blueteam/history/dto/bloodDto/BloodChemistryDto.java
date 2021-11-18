package com.blueteam.history.dto.bloodDto;

import com.blueteam.history.entity.history.exam.blood.BloodChemistry;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BloodChemistryDto {

    private BloodChemistryDto t;

    public BloodChemistryDto() {

    }

    public BloodChemistryDto(BloodChemistry bloodChemistry) {
        this.id = bloodChemistry.getId();
        this.analyzedDate = bloodChemistry.getAnalyzedDate();
        this.mCreatinine = bloodChemistry.getMCreatinine();
        this.fCreatinine = bloodChemistry.getFCreatinine();
        this.glucose = bloodChemistry.getGlucose();
        this.totalBilirubin = bloodChemistry.getTotalBilirubin();
        this.uncongjugatedBilirubin = bloodChemistry.getUnconjugatedBilirubin();
        this.congjugatedBilirubin = bloodChemistry.getConjugatedBilirubin();

    }

    private Long id;
    private LocalDate analyzedDate;
    private double mCreatinine;
    private double fCreatinine;
    private double glucose;
    private double totalBilirubin;
    private double uncongjugatedBilirubin;
    private double congjugatedBilirubin;

    public BloodChemistry convertToEntity() {
        BloodChemistry chemistry = new BloodChemistry();
        chemistry.setAnalyzedDate(this.analyzedDate);
        chemistry.setId(this.id);
        chemistry.setConjugatedBilirubin(this.congjugatedBilirubin);
        chemistry.setGlucose(this.glucose);
        chemistry.setFCreatinine(this.fCreatinine);
        chemistry.setMCreatinine(this.mCreatinine);
        chemistry.setTotalBilirubin(this.totalBilirubin);
        chemistry.setUnconjugatedBilirubin(this.uncongjugatedBilirubin);

        return chemistry;
    }

}
