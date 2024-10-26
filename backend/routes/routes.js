const express= require('express')
const route= express.Router();

const res= require('../controller/routesController')

route.get('/category',res.listCategory)
route.get('/location',res.location)
route.get('/status',res.status)
route.get('/wallet',res.wallet)
route.get('/listMenu',res.listMenu)
route.patch('/listMenu',res.buy)
route.post('/orders',res.order)

module.exports= route