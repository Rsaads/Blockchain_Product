package imd.lp.blockchain.resources;

import imd.lp.blockchain.interfaces.BlockManager;
import imd.lp.blockchain.model.Block;
import imd.lp.blockchain.model.SpecificProduct;
import imd.lp.blockchain.model.TypeCalc;
import imd.lp.blockchain.utils.BlockchainUtils;

/**
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 * @apiNote Class containing the implementations available through the interface.
 * @see BlockManager
 */
public class BlockManagerImpl implements BlockManager {

    /**
     * @param previousHash
     * @param timestamp
     * @param nonce
     * @param product
     * @param calc
     * @return
     * @apiNote The hash created is based on a set of information concatenated into a String.
     */
    @Override
    public String calculateHash(String previousHash, Long timestamp, Integer nonce, SpecificProduct product, TypeCalc calc) {
        return new BlockchainUtils().applySHA(new StringBuffer().append(previousHash).append(timestamp).append(nonce).append(product.toString()).toString(), calc);
    }

    /**
     * @apiNote This method searches for a block within the blockchain according to the parameters.
     * @param difficulty
     * @param block
     * @param calc
     * @return
     */
    @Override
    public String mineBlock(int difficulty, Block block, TypeCalc calc) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while (!block.getHash().substring(0, difficulty).equals(target)) {
            block.setNonce(block.getNonce() + 1);
            block.setHash(calculateHash(block.getPreviousHash(), block.getTimestamp(), block.getNonce(), block.getProduct(), calc));
        }
        return block.getHash();
    }
}
