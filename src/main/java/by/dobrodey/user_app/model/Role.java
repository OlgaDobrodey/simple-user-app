package by.dobrodey.user_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    private Integer roleId;
    private String roleName;

    @Override
    public String toString() {
        return roleName;
    }

    private static final Integer ID_DEFAULT_ROLE = 2;
    private static final String NAME_DEFAULT_ROLE = "USER";

    /**
     * Get default role for this date base:
     * id_role = 2;
     * name_role = "User"
     * @return Role
     */
    public static Role getDefaultRole(){
        return Role.builder()
                .roleId(ID_DEFAULT_ROLE)
                .roleName( NAME_DEFAULT_ROLE )
                .build();
    }
}
