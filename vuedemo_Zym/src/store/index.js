import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
	state: {
		products: [{
				name: 'apple',
				price: '2'
			},
			{
				name: 'banana',
				price: '3'
			},
			{
				name: 'pear',
				price: '4'
			},
			{
				name: 'melon',
				price: '5'
			},
		]
	},
	getters: {
		changeProduct: (state) => {
			return state.products.map(val => {
				return {
					name: '**' + val.name + '--',
					price: val.price * 2
				}
			});
		}
	}
})
