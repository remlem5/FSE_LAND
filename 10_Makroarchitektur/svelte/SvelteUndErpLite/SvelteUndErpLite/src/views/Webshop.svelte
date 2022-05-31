<script>
    import articles from "../Articles.json";
    import { writable, get } from "svelte/store";

    export const cart = writable({});

    /* const cartItems = get(cart); */

    let cartItems = writable([]);

    let inCart = cartItems[name] ? cartItems[name].count : 0;
    function addToCart() {
        inCart++;
        cart.update((n) => {
            return { ...n, [name]: { ...cartItems, count: inCart } };
        });
    }
</script>

<h1>Webshop</h1>
<div class="contact">
    <table>
        <th>Name</th>
        <th>Bild</th>
        <th>Preis Netto</th>
        {#each articles as item}
            <tr>
                <td width="30%">{item.productName}</td>
                <td width="0%"><img src={item.src} style="height: 3cm;" /></td>
                <td width="20%" align="center">{item.priceNet} €</td>
                <td
                    ><button class="btn btn-primary" on:click={addToCart}
                        >In den Warenkorb</button
                    ></td
                >
            </tr>
        {/each}
        {#if inCart > 0}
            <tr class="container" height="100px">
                <td><strong>Warenkorb: {inCart}</strong></td>
            </tr>
        {/if}
    </table>
</div>

<style>
    .button-minus {
        border: none;
        background-color: lightcoral;
        color: white;
        padding: 6px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 36px;
        margin: 4px 2px;
        cursor: pointer;
    }
    .button-plus {
        border: none;
        background-color: lightgreen;
        color: white;
        padding: 6px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 36px;
        margin: 4px 2px;
        cursor: pointer;
    }
</style>
