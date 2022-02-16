package imd.lp.blockchain.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 * @apiNote Represents a Product object within the system context.
 */
@Data
public class Product {

    private String name;
    private Long price;
    private Date Date;

    @Override
    public String toString() {
        return "{name='" + name + '\'' + ", price=" + price + ", Date=" + Date;
    }
}
