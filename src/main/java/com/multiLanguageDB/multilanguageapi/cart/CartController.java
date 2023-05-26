package com.multiLanguageDB.multilanguageapi.cart;

import com.multiLanguageDB.multilanguageapi.customer.Customer;
import com.multiLanguageDB.multilanguageapi.customer.CustomerCartRequest;
import com.multiLanguageDB.multilanguageapi.customer.CustomerService;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethod;
import com.multiLanguageDB.multilanguageapi.paymentMethod.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final PaymentMethodService paymentMethodService;

    private final CartResourceAssembler cartResourceAssembler;

    private final CustomerService customerService;

    @PostMapping
    @Transactional
    public ResponseEntity<CartResource> createCart(@RequestBody CustomerCartRequest customer_id) {
        Optional<Customer> customer = customerService.findByIdOptional(customer_id.getId());
        Cart cart = cartService.create(new Cart());

        customerService.update(customer.get());
        customer.get().setCart(cart);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .build(cart.getId());

        return ResponseEntity.created(location).body(cartResourceAssembler.toResource(cart));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CartResource>> getCarts() {
        return ResponseEntity.ok(cartResourceAssembler.toListResource(cartService.findAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CartResource> getCart(@PathVariable("id") UUID id) {
        Optional<Cart> cart = cartService.findByIdOptional(id);

        return cart
                .map(cartResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{cartId}/payment/{paymentId}")
    public ResponseEntity<CartResource> updateCartPaymentMethod(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("paymentId") UUID paymentId
    ) {
        Optional<Cart> cart = cartService.findByIdOptional(cartId);
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByIdOptional(paymentId);

        if (cart.isPresent() && paymentMethod.isPresent()) {
            Cart cart1 = cart.get();
            PaymentMethod paymentMethod1 = paymentMethod.get();
            cart1.setPaymentMethod(paymentMethod1);
            cartService.update(cart1);
            return ResponseEntity.ok(cartResourceAssembler.toResource(cart1));
        }
        return ResponseEntity.notFound().build();
    }
}