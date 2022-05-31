<script>
  import { onMount } from "svelte"
  
  let order_id  = "O323567890"
  $: endpoint = "http://localhost:8080/api/v1/orders/" + order_id

  $: if (order_id.length === 10){
    promise = getOrder()
  }

  async function getOrder(){
    const response = await fetch(endpoint)
    const order = await response.json()
    //console.log(order)
    if(order.errorCode) {
      throw new Error(order.errorMessage)
    } else {
      return order
    }
  }

  let promise = getOrder();

	onMount(async function () {
		promise = getOrder();
	})
</script>

<main>	

  Order-ID: <input bind:value={order_id}>
  {#if order_id.length !== 10}
    <p style="color: red">Länge ungültig</p>
  {/if}

  {#await promise}
    <p style="color: blue">Warte auf Daten.</p>
  {:then order} 
    <h1>Was willt? {order.customerFirstname}</h1>
    {#if order.state === "PAYMENT_VERIFIED"}
      <p>Vielen Dank für deine Bestellung</p>
    {:else}
      <p>No it zahlt, ha?</p>
    {/if}
  {:catch error}
    <p style="color: red">{error.message} Goht it, ha?</p>
  {/await}

</main>