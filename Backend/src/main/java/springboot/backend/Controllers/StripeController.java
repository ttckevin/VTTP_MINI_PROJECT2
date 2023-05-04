package springboot.backend.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import springboot.backend.Models.Payment.Checkout;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class StripeController {

    private static Gson gson = new Gson();

    @PostMapping("/payment")
	/**
	 * Payment with Stripe checkout page
	 * 
	 * @throws StripeException
	 */
	public String paymentWithCheckoutPage(@RequestBody Checkout payment) throws StripeException {
		// We initilize stripe object with the api key
		init();
		// We create a  stripe session parameters
		SessionCreateParams params = SessionCreateParams.builder()
				// We will use the credit card payment method 
			.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
			.setMode(SessionCreateParams.Mode.PAYMENT)
			.setSuccessUrl(payment.getSuccessUrl())
			.setCancelUrl(payment.getCancelUrl())
			.addLineItem(SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
			.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
            .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
			.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
            .setName(payment.getName()).build()).build()).build())
		    .build();
  	// create a stripe session
		Session session = Session.create(params);
		Map<String, String> responseData = new HashMap<>();
    // We get the sessionId and we putted inside the response data you can get more info from the session object
		responseData.put("id", session.getId());
	// We can return only the sessionId as a String
		return gson.toJson(responseData);
	}

    private static void init(){
        Stripe.apiKey = "sk_test_51MvyrRKw1LfXvwWculUWUawyFFnnKXAHMAPOTrTmEtJJk049HlV4X7Q9YYBAPmmQymT1ie3CEEcMFhcvQGlYJeu400X2mXiuyF";
    }

}
