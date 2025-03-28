package org.study.admin.ui.dto.users;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.study.common.utils.TimeCalculator;

@NoArgsConstructor
@AllArgsConstructor
public class GetUserTableResponseDto {

    @Getter private Long id;
    @Getter private String email;
    @Getter private String name;
    @Getter private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(updatedAt);
    }

    public String getLastLoginAt() {
        return TimeCalculator.getFormattedDate(lastLoginAt);
    }

}
