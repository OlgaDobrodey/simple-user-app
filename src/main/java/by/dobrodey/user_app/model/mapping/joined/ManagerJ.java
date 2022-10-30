package by.dobrodey.user_app.model.mapping.joined;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class ManagerJ extends Joined {

    @Column(name = "project_name")
    private String projectName;

    @Builder
    public ManagerJ(Integer id, String firstName, String lastName, String projectName) {
        super(id, firstName, lastName);
        this.projectName = projectName;
    }
}
