package imd.lp.blockchain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 * @apiNote Generic class so you can add more types of properties to a product.
 * @param <T>
 */
public class SpecificProduct<T> extends Product{
    private Map<String, T> attributes = new HashMap<>();

    public Map<String, T> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, T> attribute) {
        this.attributes = attribute;
    }

    @Override
    public String toString() {
        return super.toString() +  ", attributes=" + attributes+ "}";
    }
}
