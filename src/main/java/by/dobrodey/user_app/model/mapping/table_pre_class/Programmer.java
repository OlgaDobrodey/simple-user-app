package by.dobrodey.user_app.model.mapping.table_pre_class;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programmer extends Employee{

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public Programmer(Integer id, String firstName, String lastName, Language language) {
        super(id, firstName, lastName);
        this.language = language;
    }
}
