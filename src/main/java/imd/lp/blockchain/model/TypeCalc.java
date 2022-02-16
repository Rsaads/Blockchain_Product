package imd.lp.blockchain.model;

import lombok.Getter;

/**
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 * @apiNote Enumerated type that will serve as a basis for future calculations using another type of security algorithm.
 */
@Getter
public enum TypeCalc {
    SHA256(1,"SHA-256"), SHA512(2,"SHA-512"), MD(3,"MD"), MD2(4,"MD2"), MD4(5,"MD4"),
    MD5(6,"MD5"), MD6(7, "MD6");

    Integer id;
    String desc;

    TypeCalc(Integer id, String desc){
        this.id = id;
        this.desc = desc;
    }
}
