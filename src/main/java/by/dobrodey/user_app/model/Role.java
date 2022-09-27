package by.dobrodey.user_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Role {

    private Integer roleId;
    private String roleName;

    @Override
    public String toString() {
        return "\nRole{" +
                "id=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
