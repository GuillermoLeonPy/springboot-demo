package py.com.kyron.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable//represents relationship with another entity, this annotation not mean another entity matchs to another table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "name",//property of this bean
                column = @Column(name = "guardian_name")//column / property from another table
        ),
        @AttributeOverride(
                name = "email",//property of this bean
                column = @Column(name = "guardian_email")//column / property from another table
        ),
        @AttributeOverride(
                name = "mobile",//property of this bean
                column = @Column(name = "guardian_mobile")//column / property from another table
        )
})
public class Guardian {

    private String name;
    private String email;
    private String mobile;
}
