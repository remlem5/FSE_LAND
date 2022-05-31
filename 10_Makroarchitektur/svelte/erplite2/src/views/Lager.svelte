<script>
    import { allOrders, orderPayed } from "../../src/js/Orders.js";
    import { getPackingsByOrderId, itemPacked } from "../../src/js/Packings";

    async function onClickItemPacked(item) {
        await itemPacked(item);
    }
</script>

<h1>Lager</h1>
<div class="contact">
    {#await allOrders then orders}
        {#each orders as order}
            {#if order.state == "PAYMENT_VERIFIED"}
                <table style="width: 100%;">
                    <tr>
                        <th align="left">OrderID</th>
                        <th align="left">State</th>
                        <th align="left">Produkt</th>
                        <th align="left">Menge</th>
                        <th align="left">Verpackt?</th>
                        <th align="left" />
                        <th align="left">Produkt</th>
                        <th align="left">Menge</th>
                        <th align="left">Verpackt?</th>
                        <th align="left" />
                        <th align="left">Produkt</th>
                        <th align="left">Menge</th>
                        <th align="left">Verpackt?</th>
                        <th align="left" />
                    </tr>
                    <tr>
                        <td>{order.orderID}</td>
                        <td>{order.state}</td>
                        {#await getPackingsByOrderId(order.orderID) then items}
                            {#each items as item}
                                <td>{item.productNumber}</td>
                                <td>{item.amount}</td>
                                <td>{item.packed}</td>
                                <a
                                    href="#"
                                    on:click={() => onClickItemPacked(item)}
                                    ><td>Verpackt</td></a
                                >
                            {/each}
                        {/await}
                    </tr>
                </table>
            {:else if order.state == "PREPARING_FOR_DELIVERY"}
                <table style="width: 100%;">
                    <tr>
                        <th align="left">OrderID</th>
                        <th align="left">State</th>
                        <th align="left">Aufm LKW?</th>
                    </tr>
                    <tr>
                        <td width="20%">{order.orderID}</td>
                        <td style="color: chartreuse;">{order.state}</td>
                        <td></td>
                    </tr>
                </table>
            {/if}
        {/each}
    {/await}
</div>
