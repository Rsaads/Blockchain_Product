package imd.lp.blockchain.model;

import imd.lp.blockchain.resources.BlockManagerImpl;
import lombok.Data;

import java.util.Date;

/**
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 * @apiNote Represents a block object.
 */
@Data
public class Block {

    private String hash;
    private String previousHash;
    private SpecificProduct product;
    private long timestamp;
    private int nonce;

    public Block(SpecificProduct product, String previousHash, TypeCalc calc) {
        this.product = product;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = new BlockManagerImpl().calculateHash(previousHash, timestamp, nonce, product, calc);
    }
}