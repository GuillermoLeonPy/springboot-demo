package py.com.kyron.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "teacher")//very important to avoid exception on hibernate query operations
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"//the name of the property in the class CourseMaterial
            //when course is retrieved, course material is retrieved as well, it is not lazy ¿why?
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "studen_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id"
                    /*,
                    referencedColumnName = "studentId"
                    Unable to find column with logical name:  in org.hibernate.mapping.Table and its related supertables 
                    and secondary tables
                    
                    Please  remove the referencedColumnName attribute  which is used in Course class  
                    because student_id is the primary key field of Student, referencedColumnName = ""  
                    is only necessary if it references a non-primary-key field
                    http://beajavaite.blogspot.com/2016/06/how-to-resolve-error-caused-by.html
                    */
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
