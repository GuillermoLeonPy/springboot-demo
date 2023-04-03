package py.com.kyron.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//provide builder pattern implementation
public class Person implements Serializable/*Serializable required to work with Rabbit Mq*/{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Required ruc")
	private String ruc;
	@NotBlank(message = "Required personalName")
	private String personalName;
	private String personalLastName;
	private String personalAddress;
	@Email(message = "Invalid personalEmailAddress value")
	private String personalEmailAddress;
	private String personalTelephoneNumber;
	private String commercialName;
	private String creationUser;
	private LocalDateTime creationDate;
	private String lastUpdaterUser;
	private LocalDateTime lastUpdateDate;
	private String personalCivilIdDocument;


}
