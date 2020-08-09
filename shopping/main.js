const app = new Vue({
	el:'#app',
	data: {
		books:[
			{
				id: 1,
				name: '语文',
				data: '2001',
				price: 10.00,
				count: 1
			},
			{
				id: 2,
				name: '数学',
				data: '2002',
				price: 20.00,
				count: 1
			},
			{
				id: 3,
				name: '英语',
				data: '2003',
				price: 30.00,
				count: 1
			}

		]
	},
	methods:{
				up:function(index){
					this.books[index].count++;
					console.log('up被执行', index);
			
				},
				down:function(index){
					this.books[index].count--;
					console.log('down被执行',index);
				},
				remove:function(index){
					this.books.splice(index,1);
				}
			},
	computed:{
		total(){
			let total=0
			for(let i=0;i<this.books.length;i++){
				total+=this.books[i].price*this.books[i].count
			}
			return total
		}
	},
	filters:{
		showPrice(price){
			return'¥'+price.toFixed(2)
		}
	}
})