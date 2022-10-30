package by.dobrodey.user_app.model.mapping.single_table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "managerST")
public class ManagerST extends SingleTable {

    @Column(name = "project_name")
    private String projectName;

    @Builder
    public ManagerST(Integer id, String firstName, String lastName, String projectName) {
        super(id, firstName, lastName);
        this.projectName = projectName;
    }
}
