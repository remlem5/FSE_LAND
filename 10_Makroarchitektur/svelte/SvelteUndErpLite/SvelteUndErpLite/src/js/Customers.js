export async function getCustomer() {
    const response = await fetch("http://localhost:8080/customers/ASDFGJ1234");
    customer = await response.json();
    return customer
}

export let customer = getCustomer();
