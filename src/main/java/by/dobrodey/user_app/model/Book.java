package by.dobrodey.user_app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "library", name = "book")
public class Book extends BaseEntity<Integer> {

    private String title;
    @Column(name = "writer")
    private String author;
    @Column
    private Integer pages;

    @ManyToMany(mappedBy = "bookList")
    private List<User> users;

    @Override
    public String toString() {
        return "\nBook{" +
                "bookId=" + getId() +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages='" + pages + '\'' +
                '}';
    }
}
