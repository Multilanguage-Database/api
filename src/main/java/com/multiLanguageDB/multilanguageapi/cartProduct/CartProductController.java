package com.multiLanguageDB.multilanguageapi.cartProduct;

import com.multiLanguageDB.multilanguageapi.cart.Cart;
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

        if(cartService.findByIdOptional(cartProduct.getCart().getId()).isPresent()) {
            cartProductService.create(cartProduct);
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(cartProduct);

        return ResponseEntity.created(location).body(cartProductResourceAssembler.toResource(cartProduct));
    }

    @GetMapping
    public ResponseEntity<List<CartProductResource>> getCartProducts() {
        List<CartProduct> cartProducts = cartProductService.findAll();
        return ResponseEntity.ok(cartProductResourceAssembler.toListResource(cartProducts));
    }

    @GetMapping(path = "/{cartId}")
    public ResponseEntity<List<CartProductResource>> getCartProductsByCart(@PathVariable("cartId")UUID id) {
        Optional<Cart> cart = cartService.findByIdOptional(id);

        List<CartProductResource> cartProducts = cart.get().getProductsAssoc()
                .stream()
                .map(cartProductResourceAssembler::toResource)
                .collect(Collectors.toList());

        return ResponseEntity.ok(cartProducts);
    }

    @PutMapping(path = "/{cartId}/{productId}/{quantity}")
    public ResponseEntity<CartProductResource> updateQuantity(@PathVariable("cartId") UUID cartId,
                                                              @PathVariable("productId") UUID productId,
                                                              @PathVariable("quantity") int quantity) {
        List<Optional<CartProduct>> cartProducts = cartProductService.findByCartId(cartId);

        Optional<CartProduct> cartProduct = cartProducts
                .stream()
                .filter(cp -> cp.get().getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if(cartProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cartProduct.get().setQuantity(quantity);
        return ResponseEntity.ok(cartProductResourceAssembler.toResource(cartProductService.update(cartProduct.get())));
    }

    @DeleteMapping(path = "/{cartId}/{productId}")
    public ResponseEntity<Void>  deleteProduct(@PathVariable("cartId")UUID cartId,
                                               @PathVariable("productId")UUID productId) {
        List<Optional<CartProduct>> cartProducts = cartProductService.findByCartId(cartId);

        Optional<CartProduct> cartProduct = cartProducts
                .stream()
                .filter(cp -> cp.get().getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if(cartProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cartProductService.delete(cartProduct.get());
        return ResponseEntity.noContent().build();
    }

}
