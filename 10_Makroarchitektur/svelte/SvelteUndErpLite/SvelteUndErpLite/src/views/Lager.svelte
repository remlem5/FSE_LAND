<script>
    import { allOrders } from "../../src/js/Orders.js";
</script>

<h1>Lager</h1>
<div class="contact">
    <table style="width: 100%;">
        <tr>
            <th width="20%">OrderID</th>
            <th width="20%">State</th>
        </tr>
        {#await allOrders then orders}
            {#each orders as order}
                <tr>
                    {#if order.state == "PAYMENT_VERIFIED" || order.state == "PREPARING_FOR_DELIVERY"}
                        <td>{order.orderID}</td>
                        <td>{order.state}</td>
                        {#if order.state === "PAYMENT_VERIFIED"}
                            <button class="btn btn-primary">In Arbeit</button>
                        {:else if order.state === "PREPARING_FOR_DELIVERY"}
                            <button class="btn btn-primary"
                                >Fertig verpackt!</button
                            >
                        {/if}
                    {/if}
                </tr>
            {/each}
        {/await}
    </table>
</div>
