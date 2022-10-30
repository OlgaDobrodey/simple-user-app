package by.dobrodey.user_app.model.mapping.single_table;

import by.dobrodey.user_app.model.mapping.table_pre_class.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "programmerST") // our resolve
public class ProgrammerST extends SingleTable {

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public ProgrammerST(Integer id, String firstName, String lastName, Language language) {
        super(id, firstName, lastName);
        this.language = language;
    }

}
