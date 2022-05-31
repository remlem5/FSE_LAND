<script>
    import articles from "../lib/Articles.json";
    import { placeOrder } from "../js/Orders";

    let cart = [];

    const addToCart = (article) => {
        for (let item of cart) {
            if (item.productName === article.productName) {
                article.amount += 1;
                cart = cart;
                return;
            }
        }
        cart = [...cart, article];
    };
</script>

<h1>Webshop</h1>
<div class="contact" style="float: left;">
    <table>
        <th>Name</th>
        <th>Bild</th>
        <th>Preis Netto</th>
        {#each articles as article}
            <tr>
                <td width="30%">{article.productName}</td>
                <td width="0%"
                    ><img src={article.src} style="height: 3cm;" /></td
                >
                <td width="20%" align="center"
                    >{article.priceNet.toFixed(2)} €</td
                >
                <td
                    ><button
                        class="btn btn-primary"
                        on:click={() => addToCart(article)}
                        >In den Warenkorb</button
                    ></td
                >
            </tr>
        {/each}
    </table>
</div>

<div class="cart-list">
    {#if cart.length > 0}
        <h3>Warenkorb</h3>
        <table>
            <tr>
                <th>Produkt</th>
                <th>Menge</th>
                <th>Preis Netto</th>
                <th>Preis Brutto</th>
            </tr>
            {#each cart as item}
                <tr>
                    <td><img width="50" src={item.src} alt={item.name} /></td>
                    <td align="right">{item.amount}</td>
                    <td align="right">{item.amount * item.priceNet} €</td>
                    <td align="right"
                        >{(
                            item.amount *
                            item.priceNet *
                            ((100 + item.tax) / 100)
                        ).toFixed(2)} €</td
                    >
                </tr>
            {/each}
            <!-- goht no it -->
            <button on:click={() => placeOrder(cart)}
                >Bestellung aufgeben</button
            >
        </table>
    {/if}
</div>

<style>
    .cart-item {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        width: fit-content;
    }
    .cart-list {
        padding-left: 15cm;
    }
</style>
