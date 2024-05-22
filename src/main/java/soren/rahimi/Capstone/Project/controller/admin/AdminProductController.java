package soren.rahimi.Capstone.Project.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soren.rahimi.Capstone.Project.dto.FAQDTO;
import soren.rahimi.Capstone.Project.dto.ProductDTO;
import soren.rahimi.Capstone.Project.service.admin.adminproduct.AdminProductService;
import soren.rahimi.Capstone.Project.service.admin.faq.FAQService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    private final FAQService faqService;


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        ProductDTO productDTO1 = adminProductService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOS = adminProductService.getAllProducts();
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductByName(@PathVariable String name){
        List<ProductDTO> productDTOS = adminProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDTOS);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
            boolean deleted = adminProductService.deleteProduct(productId);
            if(deleted){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
    }


    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDTO> postFAQ(@PathVariable Long productId, @RequestBody FAQDTO faqDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDTO));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
        ProductDTO productDTO = adminProductService.getProductById(productId);
        if(productDTO != null){
            return ResponseEntity.ok(productDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDTO productDTO) throws IOException {
        ProductDTO updatedProduct = adminProductService.updateProduct(productId, productDTO);
        if(updatedProduct != null){
            return ResponseEntity.ok(updatedProduct);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
