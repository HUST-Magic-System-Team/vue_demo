//配置路由相关信息
import VueRouter from 'vue-router'
import Vue from 'vue'
import Title from "../components/Title"
import Text from "../components/Text"

//通过Vue.use 安装插件
Vue.use(VueRouter)

//创建VueRouter对象
const routes =[
    {
       path: '',
        //重定向
        redirect:'/title'
    },
    {
        path:'/title',
        component:Title
    },
    {
        path: '/text',
        component:Text
    }
]
const router =new VueRouter({
    //配置路由与组件的应用关系
    routes,
    mode:'history'
    }
)

//传入实例
export default router