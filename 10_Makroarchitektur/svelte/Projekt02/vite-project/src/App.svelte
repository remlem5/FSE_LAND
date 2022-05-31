<script>
	import Nested from "./Nested.svelte"

	let firstName = "Tobi"
	let lastName = "A."
	
	//firstName = "Claudio"
	
	let color = "blue"
	let schowNested = false
	
	const toggle = () => {
  	color = color === "blue" ? "orange" : "blue"
		schowNested = !schowNested
  }
	
	$: name = firstName + " " + lastName
</script>

<h1 style="color: {color}">Hallo {name}!</h1>
{#if schowNested}
<Nested />
{:else}<p>Nested-Component wird nicht angezeigt</p>
{/if}

<!-- <button on:click={() => color = "orange"}>Don't Press the Button</button> -->
<button on:click={toggle}>Don't Press the Button</button>
<style>
	h1 {
		color: orange;
	}
</style>