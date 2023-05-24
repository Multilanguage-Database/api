package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.CartService;
import com.multiLanguageDB.multilanguageapi.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cartproduct")
@RequiredArgsConstructor
public class CartProductController {

    private final CartProductService cartProductService;

    private final CartProductResourceAssembler cartProductResourceAssembler;

    private final ProductService productService;

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartProductResource> createCartProduct(@RequestBody CartProductRequest cartProductRequest) {

        CartProduct cartProduct = cartProductRequest.toCartProduct();

        CartProductPK cartProductPK = new CartProductPK(cartProduct.getCart().getId(), cartProduct.getProduct().getId());
        cartProduct.setCartProductPK(cartProductPK);

        cartProductService.create(cartProduct);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(cartProduct.getId());

        return ResponseEntity.created(location).body(cartProductResourceAssembler.toResource(cartProduct));
    }

    @GetMapping
    public ResponseEntity<List<CartProductResource>> getCartProducts() {
        return ResponseEntity.ok(cartProductResourceAssembler.toListResource(cartProductService.findAll()));
    }

    @GetMapping(path = "/{cartId}")
    public ResponseEntity<List<Optional<CartProductResource>>> getCartProductsByCart(@PathVariable("cartId")UUID id) {
        List<Optional<CartProduct>> cartProducts = cartProductService.findByCartId(id);

        List<ResponseEntity<Optional<CartProductResource>>> responseEntities = cartProducts
                .stream()
                .map(cartProductResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .collect(Collectors.toList());

        List<Optional<CartProductResource>> cartProductResources = responseEntities
                .stream()
                .map(ResponseEntity::getBody)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cartProductResources);
    }

    /*

    @PutMapping(path = "/cart/{cartId}/{productId}")

    @DeleteMapping{path = "/{productId}"}

     */
}
