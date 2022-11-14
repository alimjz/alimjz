package com.digipay.product.controller;

import com.digipay.product.conf.ApplicationConstants;
import com.digipay.product.exception.ErrorConstants;
import com.digipay.product.exception.ProductNotFoundException;
import com.digipay.product.model.dto.BaseResponse;
import com.digipay.product.model.dto.ProductDto;
import com.digipay.product.model.entity.Product;
import com.digipay.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.digipay.product.conf.ApplicationConstants.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService prodService;
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService prodService) {
        this.prodService = prodService;
    }


    @Operation(summary = "Insert a Product record based on Inputs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Insert a Product Instance",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = AUTHENTICATION, content = @Content)})
    @PostMapping("/products")
    public ResponseEntity<BaseResponse<Product>> stockInProductsToWarehouse(@RequestBody @Valid ProductDto productDto) {

        Product savedProduct = prodService.stockInProducts(productDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(savedProduct.getProdId())
                .toUri();
        log.info("Product Saved.");
        return ResponseEntity.created(location).body(new BaseResponse<>(HttpStatus.CREATED.value(),
                ApplicationConstants.SUCCESS));
    }

    @Operation(summary = "Query List of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Products found.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = AUTHENTICATION, content = @Content)})
    @GetMapping(value = "/products")
    public ResponseEntity<BaseResponse<List<Product>>>
    findAllProductsPaginated(@PageableDefault Pageable pageable) {

        Page<Product> result = prodService.findAllProductsList(pageable);
        return ResponseEntity.ok().body(new BaseResponse<>(HttpStatus.OK.value(), SUCCESS,
                null, result.getContent(), result.getPageable()));
    }

    @Operation(summary = "Query product based on entered ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = FOUND,
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = BADREQUEST,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = AUTHENTICATION, content = @Content),
            @ApiResponse(responseCode = "404", description = NOT_FOUND, content = @Content)})
    @GetMapping("/products/{id}")
    public ResponseEntity<BaseResponse<Optional<Product>>> findProductById(@PathVariable(name = "id") Long id) {
        Optional<Product> result = prodService.findProductById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok().body(new BaseResponse<>(HttpStatus.FOUND.value(), ApplicationConstants.FOUND,
                    null, result));
        }
        throw new ProductNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND);
    }
}
