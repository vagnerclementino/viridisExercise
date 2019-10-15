package energy.viridis.exercise.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MaintenanceOrderDto {

    @NonNull
    EquipmentDto equipment;

    @NonNull
    String scheduledDate;
}
