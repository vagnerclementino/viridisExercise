package energy.viridis.exercise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EquipmentDto {
    @NonNull
    private String name;
}
