package py.com.kyron.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")//very important to avoid exception on hibernate query operations
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne(
    		//cascade all: means that if an instance of CourseMaterial is intended to be persisted 
    		//and there is no record of Course in the database, then the Course will be persisted as well
            cascade = CascadeType.ALL,
            //when perform a select query for this table (course material), for each row retrieved: read the related course row ?
            fetch = FetchType.LAZY,
            //optional=false: means that when a course is to be persisted a course material must to be persisted as well
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
