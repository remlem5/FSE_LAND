<script>
  import { Router, Link, Route } from "svelte-navigator";
  import Webshop from "../views/Webshop.svelte";
  import OrderStatus from "../views/OrderStatus.svelte";
  import Buchhaltung from "../views/Buchhaltung.svelte";
  import Lager from "../views/Lager.svelte";
  import { getOrders } from "../../src/js/Orders.js";
  import { onMount } from "svelte";
  import { writable, get } from "svelte/store";

  onMount(async () => {
    getOrders();
  });

</script>

<Router>
  <header>
    <nav>
      <Link to="Webshop">Webshop</Link>
      <Link to="OrderStatus">OrderStatus</Link>
      <Link to="Buchhaltung">Buchhaltung</Link>
      <Link to="Lager">Lager</Link>
    </nav>
  </header>

  <main>
    <Route path="Webshop" component={Webshop}>
      <Webshop />
    </Route>

    <Route path="OrderStatus" component={OrderStatus}>
      <OrderStatus />
    </Route>

    <Route path="Buchhaltung" component={Buchhaltung}>
      <Buchhaltung />
    </Route>

    <Route path="Lager" component={Lager}>
      <Lager />
    </Route>
  </main>
</Router>

<style>
  :global(a) {
    color: darkblue;
    padding: 10pt;
    font-size: large;
    background-color: lightgray;
  }
</style>
