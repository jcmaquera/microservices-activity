# microservices-activity

1. What are the functionalities involved, and the microservices you've created?

User Services

addUser
getUserById
deleteUserById
updateUserById

Products Services

addProducts
getProductsById
deleteProductsById
getAllProducts
updateProductsById

Cart Services

addToCart
getCartById
updateCartById
cartRemove

2. Why do you think that the functionality needs to be isolated from another service?

Isolating the user service from the products and cart services makes it easier to manage and update the user service 
without impacting the other services. This also enables independent scaling of resources for each service, 
allowing them to handle varying loads independently. Moreover, by isolating functionality, 
issues in one service can be prevented from affecting the entire system, increasing the overall resilience of the system.


