package by.dobrodey.user_app.model.mapping.table_pre_class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Employee{

    @Column(name = "project_name")
    private String projectName;

    @Builder
    public Manager(Integer id, String firstName, String lastName, String projectName) {
        super(id, firstName, lastName);
        this.projectName = projectName;
    }
}
