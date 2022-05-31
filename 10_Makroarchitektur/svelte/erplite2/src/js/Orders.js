import { customer } from "./Customers";

export async function getOrders() {
    const response = await fetch("http://localhost:8080/api/v1/orders/", {method: 'GET'});
    allOrders = await response.json();
    return allOrders
}

export let allOrders = getOrders();

function createOrder(cart) {
    let cust = customer;
    console.log("CreateOrderCust", cust)
    let lineItems = [];
    let item;
    let taxTotal = 0;
    let netTotal = 0;
    let grossTotal = 0;
    cart.forEach(element => {
        item = {"productNumber": element.productNumber,
        "productName": element.productName,
        "priceNet": element.priceNet,
        "tax": element.tax,
        "amount": element.amount
    };
        lineItems.push(item)
        taxTotal += (element.amount * element.priceNet * ((100 + element.tax) / 100))
        netTotal += element.amount * element.priceNet
        grossTotal += (element.amount * element.priceNet) + (element.amount * element.priceNet * ((100 + element.tax) / 100))
})
console.log(lineItems)
    var now = new Date();
    now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
    let date = now.toISOString();
    let orderJSON = {
        "customerID": "CXU1234567",
        "customerFirstname": cust["firstname"],
        "customerLastname": cust["lastname"],
        "customerEmail": cust["email"],
        "customerStreet": cust.addressList[0].street,
        "customerZipcode": cust.addressList[0].zipcode,
        "customerCity": cust.addressList[0].city,
        "customerCountry": cust.addressList[0].country,
        "cartItems": lineItems,
        "state":"PLACED",
        "taxTotal":taxTotal,
        "netTotal":netTotal,
        "grossTotal":grossTotal,
        "date": date
    }
    return orderJSON
}

export async function placeOrder(cart){
    let order = createOrder(cart);
    console.log("ORDER", order)
    const response = await fetch("http://localhost:8080/api/v1/orders/", {method: 'POST', headers: {'Content-Type' : 'application/json'}, body: JSON.stringify(order)}).then(res => console.log("Bestellung aufgegeben", res));
    //postMessage(JSON.stringify(order), "http://localhost:3000/api/v1/orders/")
    window.location.reload()
}

export async function orderPayed(id) {
    const post = await fetch("http://localhost:8080/api/v1/orders/checkpayment/" + id , {method: 'POST'})
    window.location.reload();
}

