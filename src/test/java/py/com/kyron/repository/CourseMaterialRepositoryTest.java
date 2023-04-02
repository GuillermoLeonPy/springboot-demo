package py.com.kyron.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import py.com.kyron.entity.Course;
import py.com.kyron.entity.CourseMaterial;

import java.util.List;

import javax.persistence.CascadeType;


@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void SaveCourseMaterial() {
        Course course =
                Course.builder()
                .title("docker")
                .credit(6)
                .build();
        /* cascade all: means that if an instance of CourseMaterial is intended to be persisted 
    		and there is no record of Course in the database, then the Course will be persisted as well
            cascade = CascadeType.ALL * */        
        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                .url("test.com")
/*because of the optional = false in CourseMaterial course property, if course is not asigned, the insert will fail*/                
                .course(course)
                .build();
        
        repository.save(courseMaterial);
    }
    
    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = 
                repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }
}