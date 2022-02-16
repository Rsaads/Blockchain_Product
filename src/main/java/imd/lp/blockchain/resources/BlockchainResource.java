package imd.lp.blockchain.resources;

import imd.lp.blockchain.model.Block;
import imd.lp.blockchain.model.SpecificProduct;
import imd.lp.blockchain.model.TypeCalc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @apiNote Class responsible for receiving requests and returning processed responses.
 * @author Gildo Cordeiro, Rodrigo Saads, Jefferson Felipe
 */
@RestController
@RequestMapping(value = "/api")
public class BlockchainResource {

    private static List<Block> blockchain = new ArrayList<>();
    private BlockManagerImpl blockManager = new BlockManagerImpl();

    /**
     * @apiNote Recovers all blocks within the list (returns to Blockchain).
     * @return
     */
    @RequestMapping(value = "/blockchain", method = RequestMethod.GET)
    public ResponseEntity<?> findAllBlockChain() {
        if (blockchain.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("There are no blocks created yet");

        return ResponseEntity.ok().body(blockchain);
    }

    /**
     * @apiNote Retrieve a specific block according to the informed hash.
     * @param hash
     * @return
     */
    @RequestMapping(value = "/blockchain/{hash}", method = RequestMethod.GET)
    public ResponseEntity<?> findBlockChainByHash(@PathVariable String hash) {
        Optional<Block> blockchainFound = blockchain.stream().filter(b -> b.getHash().equals(hash)).findFirst();

        if (blockchainFound.isPresent())
            return ResponseEntity.ok().body(blockchainFound.get());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Block not found");
    }

    /**
     * @apiNote Send an object to be stored on the blockchain.
     * @param product
     * @return
     */
    @RequestMapping(value = "/blockchain", method = RequestMethod.POST)
    public ResponseEntity<?> insertProductInBlock(@RequestBody SpecificProduct product) {
        Block blockchainWithProduct;

        if (blockchain.isEmpty())
            blockchainWithProduct = new Block(product, "0", TypeCalc.SHA256);
        else
            blockchainWithProduct = new Block(product, blockchain.get(blockchain.size() - 1).getHash(), TypeCalc.SHA256);

        blockchain.add(blockchainWithProduct);
        URI uri;

        try {
            uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{hash}").buildAndExpand(blockchainWithProduct.getHash()).toUri();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating return URL");
        }
        return ResponseEntity.created(uri).build();
    }

    /**
     * @apiNote Mining a block according to a previously informed difficulty
     * @param difficulty
     * @param typeCalc
     * @param hash
     * @return
     */
    @RequestMapping(value = "mineBlock", method = RequestMethod.GET)
    public ResponseEntity<?> minerBlock(@RequestParam Integer difficulty, @RequestParam Integer typeCalc, @RequestParam String hash) {
        Block block = (Block) findBlockChainByHash(hash).getBody();
        Optional<TypeCalc> typeCalcOptional = Arrays.stream(TypeCalc.values()).filter(b -> b.getId().equals(typeCalc)).findFirst();
        String result = "";

        if(typeCalcOptional.isPresent())
           result = blockManager.mineBlock(difficulty, block, typeCalcOptional.get());

        return ResponseEntity.ok(!result.isEmpty() ? result : "Block not found in mining.");
    }
}
