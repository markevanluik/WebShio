package ee.mark.webshiospring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Person person;

    @NotNull
    @Min(0)
    private double amount;

    @ManyToMany
    @NotNull
    private List<Item> orderItems;
}
