package com.pizzeria.core;
 
import java.util.Set;
import java.util.UUID;
 
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
 
import org.hibernate.annotations.Type;
 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@MappedSuperclass
 
public @NoArgsConstructor @Getter @Setter abstract class EntityBase {
 
    @Id
    @Type(type = "uuid-char")
    private UUID id;
 
    public void validate(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator= factory.getValidator();
        Set<ConstraintViolation<EntityBase>> violations = validator.validate(this);
        if (!violations.isEmpty()) {
        throw new ConstraintViolationException(violations);}
    }
 
    @Override
    public boolean equals(Object obj)    {
        if (!(obj instanceof EntityBase)) {
            return false;
        }
        EntityBase tmp = (EntityBase)obj;
        return tmp.id.equals(this.id);
    }
 
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}