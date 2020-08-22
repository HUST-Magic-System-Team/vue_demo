<template>
	<div class="product-one">
		<h2>product-one</h2>
		<ul>
			<li v-for="item in product" v-bind:key="item" style="list-style-type:none;">
				<div class="name">{{item.name}}</div>
				<div class="price">{{item.price}}</div>
			</li>
			<li v-for="item in changeProduct" v-bind:key="item">
				<div class="name">{{item.name}}</div>
				<div class="price">{{item.price}}</div>
			</li>
		</ul>
	</div>
</template>
<script>
	export default {
		computed: {
			product() {
				return this.$store.state.products
			},
			changeProduct() {
				return this.$store.getters.changeProduct
			}
		},
	}
</script>
