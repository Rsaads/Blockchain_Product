package imd.lp.blockchain.interfaces;

import imd.lp.blockchain.model.Block;
import imd.lp.blockchain.model.SpecificProduct;
import imd.lp.blockchain.model.TypeCalc;

import java.util.List;
import java.util.Optional;

/**
 * @apiNote An interface responsible for providing methods for implementing operations for a block.
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 */
public interface BlockManager {

    String calculateHash(String previousHash, Long timestamp, Integer nonce, SpecificProduct product, TypeCalc calc);
    String mineBlock(int difficulty, Block block, TypeCalc calc);
}
