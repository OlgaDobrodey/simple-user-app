package by.dobrodey.user_app.model.mapping.joined;

import by.dobrodey.user_app.model.mapping.table_pre_class.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id") // our resolve
public class ProgrammerJ extends Joined {

    @Enumerated(EnumType.STRING)
    private Language language;

    @Builder
    public ProgrammerJ(Integer id, String firstName, String lastName, Language language) {
        super(id, firstName, lastName);
        this.language = language;
    }

}
