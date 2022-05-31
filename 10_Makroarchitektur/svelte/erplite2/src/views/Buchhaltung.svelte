<script>
    import { allOrders, orderPayed } from "../../src/js/Orders.js";

    async function onClickOrderPayed(orderID) {
        await orderPayed(orderID);
    }
</script>

<h1>Buchhaltug</h1>
<div class="contact">
    <table style="width: 100%;">
        <tr>
            <th width="20%" align="left">OrderID</th>
            <th width="20%" align="left">State</th>
            <th width="30%" align="left">Bezahlt</th>
        </tr>
        {#await allOrders then orders}
            {#each orders as order}
                <tr>
                    {#if order.state == "PLACED"}
                        <td height="50px">{order.orderID}</td>
                        <td height="50px">{order.state}</td>
                        {#if order.state === "PLACED"}
                            <td>
                                <a
                                    href="#"
                                    on:click={() =>
                                        onClickOrderPayed(order.orderID)}
                                    >Bezahlung erfolgt</a
                                ></td
                            >
                            <!-- /api/v1/orders/checkpayment/{orderid} -->
                        {/if}
                    {/if}
                </tr>
            {/each}
        {/await}
    </table>
</div>
