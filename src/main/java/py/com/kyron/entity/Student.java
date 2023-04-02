package py.com.kyron.entity;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//creates the table in the schema if not already
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//provide builder pattern implementation
@Table(name = "t_student",        
		uniqueConstraints = @UniqueConstraint(
				name = "emailid_unique",
				columnNames = "email_id")
)
public class Student {

	@Id
	@Column(name = "student_id")
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
	private Long studentId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email_id",nullable = false)
	private String emailId;

	@Embedded//represents relationship with another entity, annotated with @Embeddable
	//this annotation not mean another entity matchs to another table
    private Guardian guardian;
}
