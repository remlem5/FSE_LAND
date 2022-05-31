<script>
    import { allOrders } from "../../src/js/Orders.js";
</script>

<h1>OrderStatus</h1>
<div class="contact">
    <table style="width: 100%;">
        <tr>
            <th width="20%">OrderID</th>
            <th width="20%">State</th>
            <th>Bestellung stornieren</th>
        </tr>
        {#await allOrders then orders}
            {#each orders as order}
                <tr>
                    <td>{order.orderID}</td>
                    <td>{order.state}</td>
                    <td>
                        <button class="btn btn-primary"
                            >Storno</button
                        >
                    </td>
                </tr>
            {/each}
        {/await}
    </table>
</div>
