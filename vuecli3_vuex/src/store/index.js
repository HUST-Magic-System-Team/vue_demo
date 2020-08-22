import Vue from 'vue'
import Vuex from 'vuex'

//通过Vue.use 安装插件
Vue.use(Vuex)

//创建对象

const store =new Vuex.Store({
    //保存状态
    state: {
        counter: 2
    },
    //方法
    mutations: {
        increment(state) {
            state.counter++
        },
        decrement(state) {
            state.counter--
        }
    },
    //所需数据的获取
    getters:{
        pf(state) {
            return state.counter*state.counter
        }
        },
    // 有异步的时候， 需要action
    actions: {
        decrement (context) {
            setTimeout(function () {
                context.commit("decrement")
            }, 1000)
        }
    },
})


//导出
export default store