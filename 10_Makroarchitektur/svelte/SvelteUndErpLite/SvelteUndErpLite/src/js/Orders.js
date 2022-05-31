export async function getOrders() {
    const response = await fetch("http://localhost:8080/api/v1/orders/");
    allOrders = await response.json();
    return allOrders
}

export let allOrders = getOrders();

export async function orderPayed(id) {
    console.log(id);
        const post = await fetch(
                "http://localhost:8080/api/v1/orders/checkpayment/" +
                     id ,{method: 'POST'}
            )
            window.location.reload();
}


