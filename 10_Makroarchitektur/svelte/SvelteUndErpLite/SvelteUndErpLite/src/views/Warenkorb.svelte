<script>
    import cart from "./Webshop.svelte";
    import articles from "../Articles.json";
</script>

<div class="container">
    <div class="row">
        <table>
            <th>Name</th>
            <th>Bild</th>
            <th>Preis Netto</th>
            {#each cart as item}
                <tr>
                    <td width="30%">{item.productName}</td>
                    <td width="0%"
                        ><img src={item.src} style="height: 3cm;" /></td
                    >
                    <td width="20%" align="center">{item.priceNet} €</td>
                    <td
                        ><button class="btn btn-primary" on:click={addToCart}
                            >In den Warenkorb</button
                        ></td
                    >
                </tr>
            {/each}
        </table>
        {#each cart as item}{/each}
    </div>
</div>
