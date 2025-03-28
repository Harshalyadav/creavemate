package com.orders.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.orders.dto.FoodItemDTO;
import com.orders.dto.FoodOrderItem;
import com.orders.dto.OrderLineDTO;
import com.orders.dto.OrderRequestDTO;
import com.orders.dto.OrderRespDTO;
import com.orders.dto.RestaurantDTO;
import com.orders.dto.UserRespDTO;
import com.orders.entities.DeliveryAddress;
import com.orders.entities.Order;
import com.orders.entities.OrderLine;
import com.orders.entities.OrderStatus;
import com.orders.repository.OrderRepository;
import com.orders.service.clients.RestaurantMenuServiceClient;
import com.orders.service.clients.RestaurantServiceClient;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;
	private ModelMapper modelMapper;
	private RestaurantMenuServiceClient restaurantMenuServiceClient;
	private WebClient.Builder webClientBuilder;
	@Value("${user.get}")
	private String url;
	private RestaurantServiceClient restaurantServiceClient;
	
	//constructor based D.I - excluding @Value based url
	public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper,
			RestaurantMenuServiceClient restaurantMenuServiceClient, WebClient.Builder webClientBuilder,
			RestaurantServiceClient restaurantServiceClient) {
		super();
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
		this.restaurantMenuServiceClient = restaurantMenuServiceClient;
		this.webClientBuilder = webClientBuilder;
		this.restaurantServiceClient = restaurantServiceClient;
	}
	@Override
	public OrderRespDTO saveOrderDetails(OrderRequestDTO orderDetails) {
		/*
		 * i/p - List<FoodOrderItem- foodItemId,quantity> 
		 * and  userId n restaurantId 
		 * Development steps
		 * 1. Create Order entity - orderStatus : NEW , delivery address , customerId
		 * Later add validation of customer id from Customer MS
		 * DB action - 1 rec inserted in orders table n 1 in delivery adr table
		 * 2. Get order id.
		 * 3. From list of FoodOrderItem - create list of OrderLine
		 * 4. Establish bi dir association - Order 1<--->* OrderLine
		 * DB action - recs inserted in order_lines table with FK set 
		 * 5. generate n return order resp dto
		 */
		//1. Validate n get customer details from User MS - via WebClient
		UserRespDTO customer = getCustomerDetails(orderDetails.getCustomerId());
		//2. Validate n get restaurant details its id - from Restaurant MS  - via OpenFeign Client
		RestaurantDTO restaurant = restaurantServiceClient.findRestaurantById(orderDetails.getRestaurantId());
	
		//3. create new Order entity (transient)
		Order newOrder=new Order();
		//4. set order status
		newOrder.setOrderStatus(OrderStatus.NEW);
		//5. get delivery address from DTO n map it to DeliveryAddress entity
		DeliveryAddress address=modelMapper.map(orderDetails.getDeliveryAddress(), DeliveryAddress.class);
		//establish association : Order 1----->1 DeliveryAddress
		newOrder.setAddress(address);
		//6. Assign  customer id n restaurant id 
		newOrder.setCustomerId(orderDetails.getCustomerId());
		newOrder.setRestaurantId(orderDetails.getRestaurantId());
		//7. Set Promised delivery time (+30 minutes)
		newOrder.setDeliveryDateTime(LocalDateTime.now().plusMinutes(30));
		//8. save order entity 
		Order savedOrder = orderRepository.save(newOrder);
		//9. create n save order lines
		List<OrderLineDTO> orderLines = saveOrderLines(savedOrder,orderDetails);
		//10. Create n return order response dto		
		return generateOrderResp(savedOrder,orderLines,customer,restaurant.getName());
	}
	
	//get customer details by user id - from User MS , via WebClient API
	private UserRespDTO getCustomerDetails(Long customerId) {	
	return  webClientBuilder.build() //build load balanced Web Client
			.get() //starts building GET request
	.uri(url, customerId) //specifies URI along with URI variables
	.retrieve()	//retrieves the response
	.bodyToMono(UserRespDTO.class) //decodes the response body
	.block();//synchronous call , to block till response is received
	}

	private List<OrderLineDTO> saveOrderLines(Order order,OrderRequestDTO orderDetails)
	{
		List<OrderLineDTO> orderLines = orderDetails.getFoodOrderItems().stream()
				.map(orderItem -> mapToOrderLine(orderItem,order))
				.collect(Collectors.toList());
		return orderLines;
	}
	//map FoodOrderItem -> OrderLine
	private OrderLineDTO mapToOrderLine(FoodOrderItem orderItem,Order order)
	{
		
		/*
		 * FoodOrderItem
		 * private Long foodItemId;
		 * 	private int quantity;	
		 * 
		 * OrderLine -
		 *   private  int quantity;
		 *   private  int subTotal;
		 *   private Order order;	
		 *   private String foodItemName
		 * 
		 */
		//REST call to Restaurant Menu MS - to fetch food item details by food item  id
		FoodItemDTO foodItemDTO = restaurantMenuServiceClient
				.fetchFoodItemDetails(orderItem.getFoodItemId());
		//Create order line entity
		OrderLine orderLine=new OrderLine();
		//set food item name
		orderLine.setFoodItemName(foodItemDTO.getItemName());
		//set order quantity for this order item
		orderLine.setQuantity(orderItem.getQuantity());
		//compute n assign sub total
		orderLine.setSubTotal(foodItemDTO.getPrice()*orderItem.getQuantity());
		//establish bi dir association , Order  1<---->* OrderLine , using a helper method
		order.addOrderLine(orderLine);
		//update total order amount
		order.setOrderAmount(order.getOrderAmount()+orderLine.getSubTotal());
		//map OrderLine entity -> DTO (for adding it in the response)
		return modelMapper.map(orderLine, OrderLineDTO.class);
	}
	private OrderRespDTO generateOrderResp(Order order,
			List<OrderLineDTO> orderLines,UserRespDTO user,String restaurantName) {
		/*
		 * private Long orderId;
    
    private OrderStatus orderStatus;	

	private int orderAmount;
	
	private LocalDateTime orderDateTime;	
	
	private LocalDateTime deliveryDateTime;	

	private int deliveryCharges;

    private List<FoodItemDTO> foodItemList;
    private String  restaurantName;
    private String userName;
		 */
		//map Order -> OrderRespDTO
		OrderRespDTO respDTO = modelMapper.map(order, OrderRespDTO.class);
		//set OrderLine DTOs
		respDTO.setOrderLineList(orderLines);
		//set restaurant name
		respDTO.setRestaurantName(restaurantName);
		//set customer name
		respDTO.setCustomerName(user.getFirstName()+" "+user.getLastName());
		return respDTO;
	}

}
